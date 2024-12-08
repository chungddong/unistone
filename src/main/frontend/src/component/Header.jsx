import '../css/Header.css'
import { BsPersonCircle } from "react-icons/bs";
import { MdKeyboardArrowDown } from "react-icons/md";
import {useEffect, useState} from "react";
import axios from "axios";

function Header() {

    const [userview, setUserview] = useState(false);

    const handleLogoClick = () => {
        window.location.href = "/"; // '/' 경로로 이동
    };

    useEffect(() => {
        const fetchProjects = async () => {
            try {
                //const response = await axios.get('/api/project/list');
                //const projectNames = response.data.map((project) => project.projectName);
                //setItems(projectNames);
            } catch (error) {
                console.error('Error fetching projects:', error);
            }
        };

        fetchProjects();
    }, []);

    return (

        <div className="Header">

            <div className="LogoBox" onClick={handleLogoClick}>

                <img src="db_logo.png" height={"30px"} />

            </div>


            <div className="UserBox" onClick={() => {setUserview(!userview)}}>

                <div className="UserIcon">

                    <BsPersonCircle size={25} />

                </div>

                <div className="UserName">홍길동</div>

                <div className="EtcIcon">

                    <MdKeyboardArrowDown size={20} />

                </div>

                {userview && <Dropdown />}

            </div>

        </div>

    );

}

function Dropdown() {

    return (
        <>
            <li>마이페이지</li>
            <li>로그아웃</li>
        </>
    );
}

export default Header;



