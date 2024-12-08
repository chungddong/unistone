import React, { useState } from 'react';
import LoginPage from './LoginPage';
import '../css/Project.css';
import {MdOutlineChat, MdOutlineFilePresent, MdOutlineLibraryAddCheck} from "react-icons/md";
import WhitePage from "./WhitePage";
import FilePage from "./FilePage";

const Project = ({ onMenuClick }) => {
  
  const [activeMenu, setActiveMenu] = useState('chat'); // 기본 활성화된 메뉴: '채팅방'

  const renderPage = () => {
    switch (activeMenu) {
      case 'chat':
        return <WhitePage />;
      case 'board':
        return <LoginPage />;
      case 'documents':
        return <FilePage />;
      default:
        return <WhitePage />;
    }
  };

  return (
    <div className="project-container">
      
      <div className="project-sidebar">
        <div className='project-info'>
          <div className='project-title'>가나다라마</div>

          <div className='date'>개설일: 2024.11.14</div>
          <div className='date'>마감까지 23일</div>
        </div>

        <div className='project-item'><label>메뉴</label></div>
        <div
          className={`project-item ${activeMenu === 'chat' ? 'active' : ''}`}
          onClick={() => setActiveMenu('chat')}
        >
          <MdOutlineChat size={20}/>
          채팅방
        </div>
        <div
          className={`project-item ${activeMenu === 'board' ? 'active' : ''}`}
          onClick={() => setActiveMenu('board')}
        >
          <MdOutlineLibraryAddCheck size={20}/>
          작업 보드
        </div>
        <div
          className={`project-item ${activeMenu === 'documents' ? 'active' : ''}`}
          onClick={() => setActiveMenu('documents')}
        >
          <MdOutlineFilePresent size={20}/>
          자료 관리
        </div>

        <div className='project-user'>
          <div className='userp'><label>참여자</label></div>
          <div className='userp'>
          <img src="https://via.placeholder.com/40" alt="User" className="userp-image"/>
          <span className="userp-name">Wade Warren</span>
          </div>
          <div className='userp'>
          <img src="https://via.placeholder.com/40" alt="User" className="userp-image"/>
          <span className="userp-name">Wade Warren</span>
          </div>
          <div className='userp'>
          <img src="https://via.placeholder.com/40" alt="User" className="userp-image"/>
          <span className="userp-name">Wade Warren</span>
          </div>
          <div className='userp'>
          <img src="https://via.placeholder.com/40" alt="User" className="userp-image"/>
          <span className="userp-name">Wade Warren</span>
          </div>
          <div className='userp'>
          <img src="https://via.placeholder.com/40" alt="User" className="userp-image"/>
          <span className="userp-name">Wade Warren</span>
          </div>
        </div>


      </div>

      <div className="project-board">{renderPage()}</div>

    </div>
  );
};

export default Project;
