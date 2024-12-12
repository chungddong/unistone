import React, { useState } from "react";
import useProjectStore from "../store"; // Zustand store 임포트

function TaskModal({
                       isModalOpen,
                       handleInviteParticipant,
                       handleCancel,
                       handleCreateItem,
                   }) {
    const {
        taskName,
        setTaskName,
        startDate,
        setStartDate,
        dueDate,
        setDueDate,
        taskDescription,
        setTaskDescription,
        email,
        setEmail,
        statusNames,
        statusIds,
    } = useProjectStore();

    const [selectedStatusId, setSelectedStatusId] = useState(statusIds[0] || ""); // 드롭다운에서 선택된 상태 ID

    if (!isModalOpen) return null; // 모달이 열려있지 않으면 아무것도 렌더링하지 않음

    const handleCreate = () => {
        handleCreateItem(selectedStatusId); // 선택된 상태 ID를 handleCreateItem에 전달
    };

    return (
        <div className="modal">
            <div className="modal-content">
                <h4>새로운 작업 생성</h4>

                <div className="project-name">
                    <input
                        type="text"
                        value={taskName}
                        placeholder="작업 이름을 작성해주세요"
                        onChange={(e) => setTaskName(e.target.value)}
                    />
                    {/* 동적 드롭다운 메뉴 */}
                    <select
                        className="project-dropdown"
                        value={selectedStatusId}
                        onChange={(e) => setSelectedStatusId(e.target.value)} // 선택된 상태 ID 업데이트
                    >
                        {statusNames.map((statusName, index) => (
                            <option key={index} value={statusIds[index]}>
                                {statusName}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="date-container">
                    <div className="date-item-container">
                        <div className="date-item">
                            <label>시작일</label>
                            <input
                                type="date"
                                value={startDate}
                                onChange={(e) => setStartDate(e.target.value)}
                            />
                        </div>
                    </div>
                    <div className="date-item-container">
                        <div className="date-item">
                            <label>마감일</label>
                            <input
                                id="dueDate"
                                type="date"
                                className="date-item"
                                value={dueDate}
                                onChange={(e) => setDueDate(e.target.value)}
                            />
                        </div>
                    </div>
                </div>

                <label>작업 설명</label>
                <input
                    value={taskDescription}
                    onChange={(e) => setTaskDescription(e.target.value)}
                    placeholder="작업에 대한 설명을 작성해주세요"
                />

                <label>참여자 추가</label>
                <div className="invite-container">
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="이메일을 입력해주세요"
                    />
                    <button onClick={handleInviteParticipant}>추가</button>
                </div>

                <div className="modal-actions">
                    <button className="cancel-button" onClick={handleCancel}>
                        취소
                    </button>
                    <button className="create-button" onClick={handleCreate}>
                        생성하기
                    </button>
                </div>
            </div>
        </div>
    );
}

export default TaskModal;
