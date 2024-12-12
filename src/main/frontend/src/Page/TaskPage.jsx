import React, { useState, useEffect } from "react";
import TaskCard from "../component/TaskCard";
import "../css/TaskBoard.css";
import { MdAdd } from "react-icons/md";
import axios from "axios";
import useProjectStore from "../store";
import TaskModal from "../component/TaskModal";

function TaskPage() {
    const [loading, setLoading] = useState(true); // 로딩 상태
    const [error, setError] = useState(null); // 에러 상태

    const {
        statusNames,
        setStatusNames,
        tasks,
        statusIds,
        setStatusIds,
        setTasks,
        taskName,
        taskDescription,
        startDate,
        dueDate,
        email,
        userList,
        selectedProject,
    } = useProjectStore();

    const projectId = selectedProject.id; // 프로젝트 ID

    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleInviteParticipant = () => {
        console.log("Invite participant:");
    };

    // 프로젝트 클릭 시
    const handleCreateItem = async (selectedStatusId) => {
        try {
            const taskBoardStatusId = selectedStatusId; // 예시로 첫 번째 상태 ID 사용
            const userIds = userList.map(user => user.id); // userList에서 ID 추출

            const response = await axios.post("/api/taskboard/create", {
                taskBoard: {
                    taskName: taskName,
                    taskContent: taskDescription,
                    startDate: startDate,
                    endDate: dueDate,
                    taskBoardStatus: {
                        id: taskBoardStatusId,
                    },
                },
                projectId: projectId,
                userIds: userIds,
            });

            console.log("Task created:", response.data); // 요청 성공 시 데이터 출력
            setIsModalOpen(false); // 모달 닫기
            // 추가적인 작업이 필요하다면 여기서 처리

        } catch (error) {
            console.error("Error creating task:", error);
            setError("작업 생성 중 오류가 발생했습니다."); // 에러 상태 설정
        }
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    const handleAddClick = () => {
        setIsModalOpen(true);
    };

    // API에서 작업 상태 목록 가져오기
    useEffect(() => {
        const fetchTaskStatuses = async () => {
            setLoading(true);
            setError(null);

            try {
                const response = await axios.post("/api/taskboard/status/list", { projectId });
                const statusList = response.data.map((status) => ({
                    id: status.id,
                    name: status.statusName,
                }));

                setStatusNames(statusList.map(status => status.name));
                setStatusIds(statusList.map(status => status.id));

                // 상태 이름에 따른 초기 tasks 구조 생성
                const initialTasks = statusList.reduce((acc, status) => {
                    acc[status.name] = []; // 초기값은 빈 배열
                    return acc;
                }, {});
                setTasks(initialTasks);

                // 각 상태 ID에 대해 작업 데이터 가져오기
                await fetchTasksByStatusId(statusList);
            } catch (err) {
                setError("작업 상태를 불러오는 중 오류가 발생했습니다.");
            } finally {
                setLoading(false);
            }
        };

        fetchTaskStatuses();
    }, [projectId]);

    // 상태 ID를 이용해 작업 데이터 가져오기
    const fetchTasksByStatusId = async (statusList) => {
        const tasksData = {};

        try {
            await Promise.all(
                statusList.map(async (status) => {
                    const response = await axios.get(`/api/taskboard/${status.id}`);
                    tasksData[status.name] = response.data; // 상태 이름을 키로 하여 작업 데이터 저장
                })
            );

            setTasks(tasksData); // 작업 데이터 상태 업데이트
            console.log("Updated Tasks:", tasksData); // 업데이트된 tasks 출력
        } catch (err) {
            setError("작업 데이터를 불러오는 중 오류가 발생했습니다.");
        }
    };

    const renderHeader = (title) => (
        <div className="column-header">
            <label>
                {title} <span className="task-count"></span>
            </label>
            <div className="header-actions">
                <MdAdd size={20} onClick={handleAddClick}/>
            </div>
        </div>
    );

    if (loading) return <div>로딩 중...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div className="task-board">
            {statusNames.map((statusName) => (
                <div className="column" key={statusName}>
                    {renderHeader(statusName)}
                    {(tasks[statusName] || []).map((task) => (
                        <TaskCard key={task.id} task={task} statusName={statusName}/>
                    ))}
                </div>
            ))}

            <TaskModal
                isModalOpen={isModalOpen}
                handleInviteParticipant={handleInviteParticipant}
                handleCancel={handleCancel}
                handleCreateItem={handleCreateItem}
            />
        </div>
    );
}

export default TaskPage;
