import React from 'react';
import '../css/TaskCard.css';

const TaskCard = ({ task, statusName }) => {
    // 날짜 차이를 계산하는 함수
    const renderDateDifference = (endDate) => {
        const end = new Date(endDate);
        const today = new Date();
        const diffTime = end - today; // 밀리초 단위 차이
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); // 일 단위 차이

        if (diffDays < 0) {
            return "마감됨";
        } else {
            return `${diffDays}일 전`;
        }
    };

    return (
        <div className="task-card">
            <div className="task-info">
                <label>{task.taskName}</label>
                <p className="category">{statusName}</p>
            </div>
            <div className="description">
                <p>{task.taskContent}</p>
            </div>
            <div className="card-divider"></div>
            <div className='card-bottom'>
                <div className="date">
                    <p>{renderDateDifference(task.endDate)}</p> {/* 수정된 부분 */}
                </div>
                <div className="members">
                    {task.users?.map((user, index) => (
                        <span key={index}>{user}</span>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default TaskCard;
