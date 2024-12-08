import React from 'react';
import Header from "../component/Header";
import "../css/MainPage.css"
import ProjectBar from "../component/ProjectBar";

function MainPage() {
    return (
        <div className={"MainPage"}>
            <Header />
            <div className="PageBox">
                <ProjectBar/>
                <div className="ViewBox">

                </div>
            </div>
        </div>
    );
}

export default MainPage;
