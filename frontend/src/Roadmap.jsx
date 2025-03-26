import React, { useCallback, useState } from "react";
import ReactFlow, {
    Background,
    addEdge,
    ConnectionLineType,
    Panel,
    useNodesState,
    useEdgesState,
    ReactFlowProvider,
} from "reactflow";
import dagre from "@dagrejs/dagre";
import "reactflow/dist/style.css";
import "./custom-theme.css";
import Modal from "react-modal";
import DataConverter from './components/DataConverter';

const { initialNodes, initialEdges } = DataConverter();


const dagreGraph = new dagre.graphlib.Graph().setDefaultEdgeLabel(() => ({}));
const nodeWidth = 120;
const nodeHeight = 150;

const getLayoutedElements = (nodes, edges, direction = "TB") => {

    const isHorizontal = direction === "LR";
    dagreGraph.setGraph({ rankdir: direction });

    nodes.forEach((node) => {
        dagreGraph.setNode(node.id, { width: nodeWidth, height: nodeHeight });
    });

    edges.forEach((edge) => {
        dagreGraph.setEdge(edge.source, edge.target);
    });

    dagre.layout(dagreGraph);

    return {
        nodes: nodes.map((node) => ({
            ...node,
            targetPosition: isHorizontal ? "left" : "top",
            sourcePosition: isHorizontal ? "right" : "bottom",
            position: {
                x: dagreGraph.node(node.id).x - nodeWidth / 1.5,
                y: dagreGraph.node(node.id).y - nodeHeight / 1.5,
            },
        })),
        edges,
    };
};

// Layout
const { nodes: layoutedNodes, edges: layoutedEdges } = getLayoutedElements(
    initialNodes,
    initialEdges
);

const Roadmap = () => {
    const [nodes, setNodes, onNodesChange] = useNodesState(layoutedNodes);
    const [edges, setEdges, onEdgesChange] = useEdgesState(layoutedEdges);
    const [modalIsOpen, setModalIsOpen] = useState(false); // Controla a abertura do Modal
    const [selectedNodeInfo, setSelectedNodeInfo] = useState(null); // Armazena as informações da disciplina selecionada

    const onConnect = useCallback(
        (params) =>
            setEdges((eds) =>
                addEdge(
                    {
                        ...params,
                        type: ConnectionLineType.SmoothStep,
                        animated: true,
                        style: { strokeDasharray: "5,5", stroke: "#F57DBD" },
                    },
                    eds
                )
            ),
        []
    );

    const onLayout = useCallback(
        (direction) => {
            const { nodes: layoutedNodes, edges: layoutedEdges } = getLayoutedElements(
                nodes,
                edges,
                direction
            );

            setNodes([...layoutedNodes]);
            setEdges([...layoutedEdges]);
        },
        [nodes, edges]
    );

    const handleNodeClick = (event, node) => {
        // Abre o Modal e exibe as informações da disciplina
        setSelectedNodeInfo(node.data.info);
        setModalIsOpen(true);
    };

    const closeModal = () => {
        setModalIsOpen(false);
        setSelectedNodeInfo(null);
    };

    return (
        <ReactFlowProvider>
            <div className="roadmap-container">
                <ReactFlow
                    nodes={nodes}
                    edges={edges}
                    onNodesChange={onNodesChange}
                    onEdgesChange={onEdgesChange}
                    onConnect={onConnect}
                    onNodeClick={handleNodeClick} // Evento de clique no nó
                    connectionLineType={ConnectionLineType.SmoothStep}
                    fitView
                >
                    <Panel position="top-right">
                        <button onClick={() => onLayout("TB")}>Layout Vertical</button>
                        <button onClick={() => onLayout("LR")}>Layout Horizontal</button>
                    </Panel>
                    <Background />
                </ReactFlow>

                {/* Modal */}
                <Modal isOpen={modalIsOpen} onRequestClose={closeModal}>
                    <h2>{selectedNodeInfo?.label}</h2>
                    <div>
                        <strong>Subáreas:</strong>
                        <ul>
                            {selectedNodeInfo?.subareas?.map((subarea, index) => (
                                <li key={index}>{subarea}</li>
                            ))}
                        </ul>
                    </div>
                    <div>
                        <strong>Semestre:</strong> {selectedNodeInfo?.semestre}
                    </div>
                    <div>
                        <strong>Requisitos:</strong>
                        <ul>
                            {selectedNodeInfo?.requisitos?.map((requisito, index) => (
                                <li key={index}>{requisito}</li>
                            ))}
                        </ul>
                    </div>
                    <div>
                        <strong>Disciplinas que libera:</strong>
                        <ul>
                            {selectedNodeInfo?.libera?.map((libera, index) => (
                                <li key={index}>{libera}</li>
                            ))}
                        </ul>
                    </div>
                    <button onClick={closeModal}>Fechar</button>
                </Modal>
            </div>
        </ReactFlowProvider>
    );
};

export default Roadmap;
