import React from 'react';
import Header from "../component/Header";
import "../css/MainPage.css"
import ProjectBar from "../component/ProjectBar";
import Project from "./Project";

function MainPage() {
    return (
        <div className={"MainPage"}>
            <Header />
            <div className="PageBox">
                <ProjectBar/>
                <div className="ViewBox">

                    <Project/>

                </div>
            </div>
        </div>
    );
}

export default MainPage;
