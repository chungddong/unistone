import React, { useState, useEffect } from "react";
import "../css/FilePage.css";
import { MdAdd } from "react-icons/md";
import FileModal from "../component/FileModal";
import axios from "axios";
import useProjectStore from "../store";

function FilePage() {
    const [isModalOpen, setIsModalOpen] = useState(false); // 모달 열림 상태 관리
    const [data, setData] = useState({ files: [], links: [] }); // 서버에서 가져온 데이터
    const [loading, setLoading] = useState(true); // 로딩 상태 관리
    const [error, setError] = useState(null); // 에러 상태 관리

    const selectedProject = useProjectStore((state) => state.selectedProject);

    const projectId = selectedProject.id; // 프로젝트 ID

    // 페이지가 렌더링될 때 데이터 가져오기
    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            setError(null);
            try {
                const response = await axios.post("/api/files/list", { projectId : projectId });
                setData(response.data); // 가져온 데이터를 상태로 설정
            } catch (err) {
                setError("데이터를 가져오는 중 오류가 발생했습니다.");
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [projectId]);

    const UploadSection = ({ title, items }) => (
        <div className="uploadSection">
            <div className="sectoionBar">
                <div className="sectionTitle">{title}</div>
                <div
                    className="uploadButton"
                    onClick={() => setIsModalOpen(true)} // 모달 열기
                >
                    <MdAdd size={20} />
                </div>
            </div>
            {items.map((item) => (
                <UploadItem key={item.id} {...item} />
            ))}
        </div>
    );

    const UploadItem = ({
                            title,
                            description,
                            filePath,
                            fileType,
                            createdAt,
                            userName,
                        }) => (
        <div className="uploadItem">
            <div className="uploadType">{fileType}</div>
            <div className="uploadContent">
                {fileType === "LINK" ? (
                    <a
                        href={filePath}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="uploadTitle"
                    >
                        {title}
                    </a>
                ) : (
                    <div className="uploadTitle">{title}</div>
                )}
                {description && <div className="uploadDescription">{description}</div>}
            </div>
            <div className="uploadDate">
                {createdAt ? new Date(createdAt).toLocaleDateString() : "N/A"}
            </div>
            <div className="uploadUser">{userName}</div>
        </div>
    );

    if (loading) return <div>로딩 중...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div className="upload-page">
            <UploadSection title="업로드된 파일" items={data.files} />
            <UploadSection title="업로드된 링크" items={data.links} />
            {isModalOpen && (
                <FileModal
                    onClose={() => setIsModalOpen(false)} // 모달 닫기
                />
            )}
        </div>
    );
}

export default FilePage;
