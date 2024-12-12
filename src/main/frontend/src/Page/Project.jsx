import React, { useState, useEffect } from 'react';
import '../css/Project.css';
import {MdOutlineChat, MdOutlineFilePresent, MdOutlineLibraryAddCheck, MdOutlinePersonPin} from "react-icons/md";
import WhitePage from "./WhitePage";
import FilePage from "./FilePage";
import useProjectStore from "../store";
import ChatPage from "./ChatPage";
import TaskPage from "./TaskPage";
import {BsPersonCircle} from "react-icons/bs";

const Project = ({ onMenuClick }) => {
  const [activeMenu, setActiveMenu] = useState('chat'); // 기본 활성화된 메뉴: '채팅방'

  const selectedProject = useProjectStore((state) => state.selectedProject);
  const userList = useProjectStore((state) => state.userList); // Zustand에서 userList 가져오기

  useEffect(() => {
    setActiveMenu('whitePage'); // selectedProject 변경 시 기본 메뉴 설정
  }, [selectedProject]);

  const renderPage = () => {
    switch (activeMenu) {
      case 'chat':
        return <ChatPage />;
      case 'board':
        return <TaskPage />;
      case 'documents':
        return <FilePage />;
      case 'whitePage': // 기본 페이지
      default:
        return <ChatPage />;
    }
  };

  return (
      <div className="project-container">
        <div className="project-sidebar">
          <div className='project-info'>
            {selectedProject ? (
                <div className="project-info">
                  <div className="project-title">{selectedProject.projectName}</div>
                  <div className="date">개설일: {new Date(selectedProject.startDate).toLocaleDateString()}</div>
                  <div className="date">마감일: {new Date(selectedProject.endDate).toLocaleDateString()}</div>
                  <div className="description">{selectedProject.description}</div>
                </div>
            ) : (
                <div className="project-info">프로젝트를 선택해주세요.</div>
            )}
          </div>

          <div className='project-item'><label>메뉴</label></div>
          <div
              className={`project-item ${activeMenu === 'chat' ? 'active' : ''}`}
              onClick={() => setActiveMenu('chat')}
          >
            <MdOutlineChat size={20} />
            채팅방
          </div>
          <div
              className={`project-item ${activeMenu === 'board' ? 'active' : ''}`}
              onClick={() => setActiveMenu('board')}
          >
            <MdOutlineLibraryAddCheck size={20} />
            작업 보드
          </div>
          <div
              className={`project-item ${activeMenu === 'documents' ? 'active' : ''}`}
              onClick={() => setActiveMenu('documents')}
          >
            <MdOutlineFilePresent size={20} />
            자료 관리
          </div>

          <div className='project-user'>
            <div className='userp'><label>참여자</label></div>
            {/* 유저 리스트 출력 */}
            {userList.length > 0 ? (
                userList.map((user, index) => (
                    <div className='userp' key={index}>
                      <BsPersonCircle size={25} />
                      <span className="userp-name">{user}</span>
                    </div>
                ))
            ) : (
                <div className="userp">참여자가 없습니다.</div>
            )}
          </div>
        </div>

        <div className="project-board">{renderPage()}</div>
      </div>
  );
};

export default Project;
