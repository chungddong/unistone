import React from "react";

function Modal({
                   isModalOpen,
                   projectName,
                   setProjectName,
                   startDate,
                   setStartDate,
                   hasDueDate,
                   toggleDueDate,
                   dueDate,
                   setDueDate,
                   projectDescription,
                   setProjectDescription,
                   email,
                   setEmail,
                   handleInviteParticipant,
                   handleCancel,
                   handleCreateItem,
               }) {
    if (!isModalOpen) return null; // 모달이 열려있지 않을 경우 아무것도 렌더링하지 않음

    return (
        <div className="modal">
            <div className="modal-content">
                <h5>새로운 프로젝트 생성</h5>

                {/* 프로젝트 이름 */}
                <div className="project-name">
                    <input
                        type="text"
                        value={projectName}
                        placeholder="프로젝트 이름을 작성해주세요"
                        onChange={(e) => setProjectName(e.target.value)}
                        onFocus={() => setProjectName("")} // 클릭 시 기본 문구 사라짐
                    />
                </div>

                {/* 작성일과 마감일 */}
                <div className="date-container">
                    <div className="date-item-container">
                        <div className="date-item">
                            <label>작성일</label>
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
                            <div>
                                <div className="toggle-due-date" onClick={toggleDueDate}>
                                    {hasDueDate ? "Have due date" : "No due date"}
                                </div>
                                {hasDueDate && (
                                    <input
                                        id="dueDate"
                                        type="date"
                                        className="date-item"
                                        value={dueDate}
                                        onChange={(e) => setDueDate(e.target.value)}
                                    />
                                )}
                            </div>
                        </div>
                    </div>
                </div>

                {/* 프로젝트 설명 */}
                <label>프로젝트 설명</label>
                <textarea
                    value={projectDescription}
                    onChange={(e) => setProjectDescription(e.target.value)}
                    placeholder="프로젝트에 대한 설명을 작성해주세요"
                />

                {/* 참여자 추가 */}
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

                {/* 취소 버튼, 생성하기 버튼 */}
                <div className="modal-actions">
                    <button className="cancel-button" onClick={handleCancel}>
                        취소
                    </button>
                    <button className="create-button" onClick={handleCreateItem}>
                        생성하기
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Modal;
