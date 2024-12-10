import React, { useState } from "react";
import axios from "axios";
import useProjectStore from "../store";


function FileModal({ onClose, projectId }) {
    const [items, setItems] = useState([]); // 드래그/붙여넣기 항목 관리
    const [error, setError] = useState(null); // 에러 메시지 관리

    const selectedProject = useProjectStore((state) => state.selectedProject);


    // 클립보드 붙여넣기 이벤트 처리
    const handlePaste = (event) => {
        const pastedData = event.clipboardData.getData("text");

        if (pastedData.startsWith("http://") || pastedData.startsWith("https://")) {
            setItems((prevItems) => [...prevItems, { type: "link", value: pastedData }]);
        }
    };

    // 드래그 앤 드롭 이벤트 처리
    const handleDrop = (event) => {
        event.preventDefault();
        const files = event.dataTransfer.files;

        const fileObjects = Array.from(files).map((file) => ({ type: "file", value: file }));
        setItems((prevItems) => [...prevItems, ...fileObjects]);
    };

    // 업로드 요청 처리
    const handleCreate = async () => {
        setError(null); // 이전 에러 초기화

        console.log("업로드 요청");
        console.log(selectedProject.id + "가 아이디임");

        try {
            for (const item of items) {
                if (item.type === "file") {
                    // 파일 업로드 요청
                    const formData = new FormData();
                    formData.append("file", item.value);
                    formData.append("title", "Uploaded File");
                    formData.append("description", "File uploaded via React");
                    formData.append("projectId", selectedProject.id);

                    await axios.post("/api/files/create/file", formData, {
                        headers: { "Content-Type": "multipart/form-data" },
                    });
                } else if (item.type === "link") {

                    // 링크 업로드 요청
                    await axios.post("/api/files/create/link",
                        new URLSearchParams({
                            link: item.value,  // 실제 link 값 전달
                            title: "Uploaded Link",
                            description: "Link uploaded via React",
                            projectId: selectedProject.id,
                        }), {
                            headers: { "Content-Type": "application/x-www-form-urlencoded" }
                        }
                    );
                }
            }

            alert("업로드 완료!");
            onClose();
        } catch (err) {
            setError("업로드 중 오류가 발생했습니다.");
        }
    };


    return (
        <div className="modal" onPaste={handlePaste}>
            <div className="modal-content">
                <h4>파일 & 링크 업로드</h4>

                {/* 드래그 앤 드롭 박스 */}
                <div
                    className="DropBox"
                    onDragEnter={(event) => event.preventDefault()}
                    onDragOver={(event) => event.preventDefault()}
                    onDrop={handleDrop}
                >
                    <div className="DropBoxIcon"></div>
                    <div className="DropBoxText">파일 또는 링크를 드래그 앤 드롭하세요.</div>
                </div>

                {/* 추가된 목록 */}
                <div className="BoxTitle">추가된 목록</div>
                <div className="DropListBox">
                    {items.map((item, index) => (
                        <div key={index} className="list-item">
                            {item.type === "file" ? item.value.name : item.value}
                        </div>
                    ))}
                </div>

                {/* 에러 메시지 표시 */}
                {error && <div className="error-message">{error}</div>}

                {/* 취소 버튼, 생성하기 버튼 */}
                <div className="modal-actions">
                    <button className="cancel-button" onClick={onClose}>
                        취소
                    </button>
                    <button className="create-button" onClick={handleCreate}>
                        업로드
                    </button>
                </div>
            </div>
        </div>
    );
}

export default FileModal;
