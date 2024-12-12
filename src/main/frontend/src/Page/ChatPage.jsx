import React, { useState } from "react";
import "../css/ChatPage.css";
import {IoMdNotifications, IoMdSend} from "react-icons/io";
import {MdOutlineKeyboardArrowDown, MdSupervisedUserCircle} from "react-icons/md";

function ChatPage() {
    const [sidebarData, setSidebarData] = useState({
        공지사항: [],
        스크랩: [],
    });

    const addToSidebar = (message, category) => {
        setSidebarData((prevData) => ({
            ...prevData,
            [category]: [...prevData[category], message],
        }));
    };

    return (
        <div className="chat-container">
            <ChatBox addToSidebar={addToSidebar} />
            <Sidebar sidebarData={sidebarData} />
        </div>
    );
}

function ChatBox({ addToSidebar }) {
    const [message, setMessage] = useState("");
    const [messages, setMessages] = useState([
        { text: "안녕하세요!", sender: "other", time: "now", image: "https://via.placeholder.com/30" },
        { text: "팀채팅을 시작해보세요!", sender: "other", time: "now", image: "https://via.placeholder.com/30" },
    ]);

    const [popupVisible, setPopupVisible] = useState(false);
    const [selectedMessage, setSelectedMessage] = useState(null);

    const handleSendMessage = () => {
        if (message.trim() === "") return;

        const currentTime = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
        setMessages([...messages, { text: message, sender: "me", time: currentTime, image: "https://randomuser.me/api/portraits/women/3.jpg" }]);
        setMessage("");
    };

    const handleKeyDown = (e) => {
        if (e.key === "Enter") {
            e.preventDefault();
            handleSendMessage();
        }
    };

    const handleMessageClick = (message) => {
        setSelectedMessage(message);
        setPopupVisible(true);
    };

    const handlePopupClose = () => {
        setPopupVisible(false);
        setSelectedMessage(null);
    };

    const handleAddToCategory = (category) => {
        if (selectedMessage) {
            addToSidebar(selectedMessage, category);
            alert(`${category}에 추가되었습니다.`);
        }
        handlePopupClose();
    };

    const handleFileClick = () => {
        alert("파일 첨부 기능 구현 필요");
    };

    return (
        <div className="chat-box">

            <div className="chat-header">전체 채팅</div>

            <div className="chat-room">
                <div className="chat-messages">
                    {messages.map((msg, index) => (
                        <div
                            key={index}
                            className="message-container"
                            onClick={() => handleMessageClick(msg)}
                        >
                            {msg.sender === "me" && (
                                <div className="my-message">
                                    <p>{msg.text}</p>
                                    <span className="message-time my-time">{msg.time}</span>
                                </div>
                            )}
                            {msg.sender === "other" && (
                                <div className="other-message">
                                    <div className="message-info">
                                        <MdSupervisedUserCircle className="user-image" />
                                        <p>{msg.text}</p>
                                    </div>
                                    <span className="message-time other-time">{msg.time}</span>
                                </div>
                            )}
                        </div>
                    ))}
                </div>
                <div className="chat-input">
                    <input
                        type="text"
                        value={message}
                        onChange={(e) => setMessage(e.target.value)}
                        onKeyDown={handleKeyDown}
                        placeholder="메시지를 입력하세요..."
                    />
                    <div className="chat-buttons">
                        <IoMdSend />
                    </div>
                </div>
            </div>
            {popupVisible && (
                <div className="popup-overlay" onClick={handlePopupClose}>
                    <div className="popup" onClick={(e) => e.stopPropagation()}>
                        <h4>추가할 항목 선택</h4>
                        <div className="popup-options">
                            <button onClick={() => handleAddToCategory("공지사항")}>공지사항</button>
                            <button onClick={() => handleAddToCategory("스크랩")}>스크랩</button>
                        </div>
                        <button className="popup-close" onClick={handlePopupClose}>
                            닫기
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
}

function Sidebar({ sidebarData }) {

    const [dropdowns, setDropdowns] = useState({
        공지사항: false,
        스크랩: false,
    });

    const toggleDropdown = (category) => {
        setDropdowns((prev) => ({
            ...prev,
            [category]: !prev[category],
        }));
    };

    return (
        <div className="chat-sidebar">
            {Object.entries(sidebarData).map(([category, items]) => (
                <div key={category} className="chat-item">
                    <div className="chat-item-header" onClick={() => toggleDropdown(category)}>
                        <div className="chat-item-content">
                            <IoMdNotifications className="chat-icon" />
                            <span>{category}</span>
                        </div>
                        <MdOutlineKeyboardArrowDown className="chat-arrow"/>
                    </div>
                    {dropdowns[category] && (
                        <div className="dropdown">
                            {sidebarData[category].length > 0 ? (
                                sidebarData[category].map((msg, index) => (
                                    <div key={index} className="dropdown-item">
                                        {msg.text}
                                    </div>
                                ))
                            ) : (
                                <div className="dropdown-item empty">추가된 메시지가 없습니다.</div>
                            )}
                        </div>
                    )}
                </div>
            ))}
        </div>
    );
}

export default ChatPage;