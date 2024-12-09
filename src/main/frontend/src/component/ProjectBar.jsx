import React, {useEffect, useState} from "react";
import {MdOutlineSettings} from "react-icons/md";
import Modal from "./Modal"; // Modal 컴포넌트 임포트
import "../css/ProjectBar.css";
import axios from "axios";
import useProjectStore from "../store";


function ProjectBar() {
    const [items, setItems] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);

    const [projectName, setProjectName] = useState("");
    const [startDate, setStartDate] = useState("");
    const [dueDate, setDueDate] = useState("");
    const [hasDueDate, setHasDueDate] = useState(false);
    const [projectDescription, setProjectDescription] = useState("");
    const [email, setEmail] = useState("");


    //const setSelectedProject = useProjectStore((state) => state.setSelectedProject);
    //const setUserList = useProjectStore((state) => state.userList);

    const { setSelectedProject, setUserList } = useProjectStore.getState();

    // 페이지 로드 시 프로젝트 목록 가져오기
    useEffect(() => {
        const fetchProjects = async () => {
            try {
                const response = await axios.get('/api/project/list');
                //const projectNames = response.data.map((project) => project.projectName);
                setItems(response.data);
            } catch (error) {
                console.error('Error fetching projects:', error);
            }
        };

        fetchProjects();
    }, []);

    const handleAddClick = () => {
        setIsModalOpen(true);
    };

    const toggleDueDate = () => {
        setHasDueDate(!hasDueDate);
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    const handleCreateItem = async (e) => {
        e.preventDefault();
        try {
            await axios.post('/api/project/create', {
                projectName,
                startDate,
                endDate: dueDate,
                description: projectDescription,
            }, {withCredentials: true});

            // 업데이트된 목록 다시 로드
            const pjlistResponse = await axios.get('/api/project/list');
            const projectNames = pjlistResponse.data.map((project) => project.projectName);
            setItems(projectNames);
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error creating project:', error);
        }
    };

    const handleInviteParticipant = () => {
        console.log("Invite participant:", email);
    };

    // 프로젝트 클릭 시
    const handleItemClick = async (id) => {
        try {
            const response = await axios.post("/api/project/info", {projectId: id});
            setSelectedProject(response.data); // 상태 업데이트

            const userResponse = await axios.post("/api/project/users", {projectId: id});
            setUserList(userResponse.data);

            //console.log("유저 목록 : " + userResponse.data);
        } catch (error) {
            console.error("Error fetching project info:", error);
        }
    };

    return (
        <div className="ProjectBar">
            {/* 생성된 프로젝트 */}
            <div className="ProjectBox">
                <div className="items">
                    {items.map((item) => (
                        <div
                            key={item.id} // 올바른 key 사용
                            className="item"
                            onClick={() => handleItemClick(item.id)} // item.id가 존재
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
                <MdOutlineSettings size={25} className="settings-icon"/>
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
