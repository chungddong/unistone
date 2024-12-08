import React, { useState } from "react";
import { MdOutlineSettings } from "react-icons/md";
import Modal from "./Modal"; // Modal 컴포넌트 임포트
import "../css/ProjectBar.css";

function ProjectBar() {
    const [items, setItems] = useState([]);
    const [activeProject, setActiveProject] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);

    // 모달 내부 상태 관리
    const [projectName, setProjectName] = useState("");
    const [startDate, setStartDate] = useState("");
    const [dueDate, setDueDate] = useState("");
    const [hasDueDate, setHasDueDate] = useState(false);
    const [projectDescription, setProjectDescription] = useState("");
    const [email, setEmail] = useState("");

    const handleAddClick = () => {
        setIsModalOpen(true);
    };

    const toggleDueDate = () => {
        setHasDueDate(!hasDueDate);
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    const handleCreateItem = () => {
        const newItem = { projectName, startDate, dueDate, projectDescription };
        setItems([...items, newItem]);
        setIsModalOpen(false);
    };

    const handleInviteParticipant = () => {
        console.log("Invite participant:", email);
    };

    return (
        <div className="ProjectBar">
            {/* 생성된 프로젝트 */}
            <div className="ProjectBox">
                <div className="items">
                    {items.map((item, index) => (
                        <div
                            key={index}
                            className="item"
                            onClick={() => setActiveProject(item)}
                        >
              <span className="item-first-letter">
                {item.projectName.charAt(0)}
              </span>
                        </div>
                    ))}

                    {/* 프로젝트 추가 */}
                    <div className="add-button" onClick={handleAddClick}>
                        +
                    </div>
                </div>
            </div>

            {/* 설정 */}
            <div className="settings">
                <MdOutlineSettings size={25} className="settings-icon" />
            </div>

            {/* Modal */}
            <Modal
                isModalOpen={isModalOpen}
                projectName={projectName}
                setProjectName={setProjectName}
                startDate={startDate}
                setStartDate={setStartDate}
                hasDueDate={hasDueDate}
                toggleDueDate={toggleDueDate}
                dueDate={dueDate}
                setDueDate={setDueDate}
                projectDescription={projectDescription}
                setProjectDescription={setProjectDescription}
                email={email}
                setEmail={setEmail}
                handleInviteParticipant={handleInviteParticipant}
                handleCancel={handleCancel}
                handleCreateItem={handleCreateItem}
            />
        </div>
    );
}

export default ProjectBar;
