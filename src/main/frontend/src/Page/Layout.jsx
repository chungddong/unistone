import React, {useState} from "react";
import "../css/Layout.css";

const Layout = () => {

  const [items, setItems] = useState([]); // 항목 리스트
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [activeProject, setActiveProject] = useState(null); // 활성화된 프로젝트
  const [projectName, setProjectName] = useState(""); // 프로젝트 이름
  const [startDate, setStartDate] = useState(""); // 작성일
  const [hasDueDate, setHasDueDate] = useState(false);
  const [dueDate, setDueDate] = useState(""); // 마감일
  const [projectDescription, setProjectDescription] = useState(""); // 프로젝트 설명
  const [email, setEmail] = useState(""); // 이메일

  // 추가 버튼 클릭 - 모달창 오픈
  const handleAddClick = () => {
    setIsModalOpen(true);
  };

  // 생성하기 버튼 클릭 - 항목(프로젝트) 추가
  const handleCreateItem = (/*projectName, startDate, dueDate, projectDescription*/) => {
    const newItem = {
      id: Date.now(),
      name: projectName,
      description: projectDescription,
    };
    setItems([
      ...items,
      { projectName, startDate, dueDate }
    ]);
    setActiveProject(newItem); // 새로 생성된 항목을 활성 프로젝트로 설정
    setIsModalOpen(false); // 모달창 닫기
    setProjectName("");
    setStartDate("");
    setDueDate("");
  };

  // 취소 버튼 클릭 - 모달창 닫기
  const handleCancel = () => {
    setIsModalOpen(false);
    setProjectName("");
    setStartDate("");
    setDueDate("");
  };

  const toggleDueDate = () => {
    setHasDueDate(!hasDueDate);
    if (!hasDueDate) {
      setDueDate(""); 
    }
  };

  const handleInviteParticipant = () => {
    alert(`초대 링크를 ${email}으로 전송합니다.`);
    setEmail(""); 
  };

  return (
    <div className="container">
      <aside className="sidebar">

        {/* 로고 */}
        <div className="logo">
          <img
            src="logo.png" // 로고 이미지
            alt="Logo"
            className="logo-image"
          />
        </div>

        {/* 구분선 */}
        <div className="divider"></div>
        
        {/* 생성된 프로젝트 */}
        <div className="items">
          {items.map((item, index) => (
            <div
              key={index}
              className="item"
              onClick={() => setActiveProject(item)}
              >
              <span className="item-first-letter">
                {item.projectName.charAt(0)} //{/* 프로젝트 이름 첫 글자만 표시 */}
              </span>
            </div>
          ))}
          
          {/* 프로젝트 추가 */}
          <div className="add-button" onClick={handleAddClick}>
            +
          </div>
        </div>
        
        {/* 설정 */}
        <div className="settings">
        <img
            src="setting.png" // 설정 아이콘 이미지
            alt="Settings"
            className="settings-icon"
            onClick={() => alert("설정 페이지로 이동합니다")}
          />
        </div>
      </aside>
      
      <header className="header">
        {/* 사이트 이름 */}
        <div className="header-left">Unistone</div>

        {/* 사용자 */}
        <div className="header-right">
          <img
            src="user.png" // 사용자 이미지
            alt="User"
            className="user-image"
          />
          <span className="user-name">사용자</span>
          <img
            src="arrow.png" // 화살표 이미지
            alt="Dropdown"
            className="dropdown-arrow"
          />
        </div>
      </header>
      
      {/* 메인 화면 */}
      <main className="content">
        {activeProject ? (
          <div />
        ) : (

          <p>유니스톤, 조별과제가 쉬워지는 순간<br />
          프로젝트를 선택하거나 새로 생성하세요</p>
        )}
      </main>

      {/* 모달창 */}
      {isModalOpen && (
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
                    <div
                      className="toggle-due-date"
                      onClick={toggleDueDate}
                    >
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
              <button className="cancel-button" onClick={handleCancel}>취소</button>
              <button className="create-button" onClick={handleCreateItem}>생성하기</button>
            </div>
          </div>
        </div>
      )}

    </div>
  );
};


export default Layout;
