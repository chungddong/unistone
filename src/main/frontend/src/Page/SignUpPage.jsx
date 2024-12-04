import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "../css/LoginPage.css";
import { MdOutlineMailOutline } from "react-icons/md";
import { MdOutlinePassword } from "react-icons/md";
import { FaUser } from "react-icons/fa6";
import axios from "axios";

function LoginPage() {
    const [userName, setUserName] = useState('');
    const [userEmail, setUserEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = (e) => {
        e.preventDefault();

        //서버로 로그인 POST 요청
        axios.post('/api/signup', {
            userName : userName,
            email: userEmail,
            passwd: password
        },{withCredentials : true})
            .then((response) => {
                if (response.data === "Confirm") {
                    console.log('회원가입 성공 :', response.data);

                    window.location.href = '/';
                }
            })
            .catch((error) => {
                console.error('회원가입 에러 :', error);
            });
    };

    return (
        <div className="App">

            <div className="LeftBox">

                <div className="logobox">
                    Unistone
                </div>

                <div className="SliderImageBox">
                    <img src="/sliderImage.png"/>
                </div>

                <div className="SliderBox">
                    <div className="SliderText">
                        <h2>유니스톤, 조별과제가 쉬워지는 순간</h2>
                        조원 관리,작업관리, 채팅을 한 곳에서 관리해보세요
                    </div>

                    <div className="Slider">

                    </div>
                </div>

            </div>

            <div className="RightBox">

                <div className="SignBox">

                    <div className="SignTitle">회원가입</div>
                    <div className="SignSubTitle"></div>

                    <form className="SignForm" onSubmit={handleLogin}>

                        <div className="SignInputBox">
                            <div className="InputBoxIcon">

                                <FaUser size={15}/>

                            </div>
                            <input type={"text"} className="SignInput" id="UserName" placeholder="유저명"
                                   value={userName}
                                   onChange={(e) => setUserName(e.target.value)}/>
                            <div className="InputEndIcon">

                            </div>
                        </div>

                        <div className="SignInputBox">
                            <div className="InputBoxIcon">

                                <MdOutlineMailOutline size={20}/>

                            </div>
                            <input type={"email"} className="SignInput" id="UserName" placeholder="이메일"
                                   value={userEmail}
                                   onChange={(e) => setUserEmail(e.target.value)}/>
                            <div className="InputEndIcon">

                            </div>
                        </div>

                        <div className="SignInputBox">
                            <div className="InputBoxIcon">

                                <MdOutlinePassword size={20}/>

                            </div>
                            <input type={"password"} className="SignInput" id="UserName" placeholder="비밀번호"
                                   value={password}
                                   onChange={(e) => setPassword(e.target.value)}/>
                            <div className="InputEndIcon">

                            </div>
                        </div>

                        <div className="SignInputBox">
                            <div className="InputBoxIcon">

                                <MdOutlinePassword size={20}/>

                            </div>
                            <input type={"password"} className="SignInput" id="UserName" placeholder="비밀번호 확인"
                                   value={password}
                                   onChange={(e) => setPassword(e.target.value)}/>
                            <div className="InputEndIcon">

                            </div>
                        </div>

                        <button type={"submit"} name="SignIn">회원가입</button>

                    </form>

                    <div className="SignEtcLink">이미 가입하셨나요? &nbsp; <a href={"/"}>로그인</a></div>

                </div>

            </div>

        </div>
    );
}

export default LoginPage;
