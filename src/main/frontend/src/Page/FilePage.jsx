import "../css/FilePage.css"

function FilePage() {

    const data = {
        files: [
            {
                id: 1,
                title: "순서도 샘플",
                description: "순서도 작성한 pdf 파일입니다.",
                filePath: "/path/to/file1",
                fileType: "FILE",
                createdAt: "2024-11-28T10:00:00",
                userName: "서충만",
            },
            {
                id: 2,
                title: "프로젝트 제안서",
                description: "프로젝ㅌ트 제안서입니다",
                filePath: "/path/to/file2",
                fileType: "FILE",
                createdAt: "2024-11-28T11:00:00",
                userName: "장우영",
            },
        ],
        links: [
            {
                id: 3,
                title: "테스트 사이트",
                description: "테스트용 사이트입니다.",
                filePath: "http://example1.com",
                fileType: "LINK",
                createdAt: "2024-11-28T12:00:00",
                userName: "서김환희",
            },
            {
                id: 4,
                title: "프로젝트 샘플 링크",
                description: "프로젝트 제작 관련 참고 링크",
                filePath: "http://example2.com",
                fileType: "LINK",
                createdAt: "2024-11-28T13:00:00",
                userName: "교수님",
            },
        ],
    };

    const UploadSection = ({ title, items }) => (
        <div className="uploadSection">
            <div className="sectoionBar">
                <div className="sectionTitle">{title}</div>
                <button className="uploadButton">업로드</button>
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
            <div className="uploadDate">{new Date(createdAt).toLocaleDateString()}</div>
            <div className="uploadUser">{userName}</div>
        </div>
    );


    return (
        <div className="upload-page">
            <UploadSection title="업로드된 파일" items={data.files} />
            <UploadSection title="업로드된 링크" items={data.links} />
        </div>
    );


}

export default FilePage;