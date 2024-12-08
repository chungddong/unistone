import '../css/Header.css'
import { BsPersonCircle } from "react-icons/bs";
import { MdKeyboardArrowDown } from "react-icons/md";

function Header() {

    const handleLogoClick = () => {
        window.location.href = "/"; // '/' 경로로 이동
    };

    return (

        <div className="Header">

            <div className="LogoBox" onClick={handleLogoClick}>

                <img src="db_logo.png" height={"30px"} />

            </div>


            <div className="UserBox">

                <div className="UserIcon">

                    <BsPersonCircle size={25} />

                </div>

                <div className="UserName">홍길동</div>

                <div className="EtcIcon">

                    <MdKeyboardArrowDown size={20} />

                </div>

            </div>

        </div>

    );

}

export default Header;