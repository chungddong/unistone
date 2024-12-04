import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "../css/LoginPage.css";
import { MdOutlineMailOutline } from "react-icons/md";
import { MdOutlinePassword } from "react-icons/md";

function LoginPage() {
    const [userEmail, setUserEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = (e) => {
        e.preventDefault();

        // 로그인 로직
        if (userEmail === 'user@user' && password === 'pass') {
            // 로그인 성공 시 메인 페이지로 이동
            navigate('/main');
        } else {
            alert('Invalid username or password');
        }
    };

    return (
        <div className="App">

            <div className="LeftBox">

                <div className="logobox">

                </div>

                <div className="SliderImageBox">

                </div>

                <div className="SliderBox">
                    <div className="SliderText">
                        <h2>유니스톤, 조별과ㅜ제가 쉬워지는 순간</h2>
                        조원 관리,작업관리, 채팅을 한 곳에서 관리해보세요
                    </div>

                    <div className="Slider">

                    </div>
                </div>

            </div>

            <div className="RightBox">

                <div className="SignBox">

                    <div className="SignTitle">로그인</div>
                    <div className="SignSubTitle">돌아오신 것을 환영합니다! 로그인을 위해서 입력해주세요</div>

                    <form className="SignForm" onSubmit={handleLogin}>

                        <div className="SignInputBox">
                            <div className="InputBoxIcon">

                                <MdOutlineMailOutline size={20} />

                            </div>
                            <input type={"text"} className="SignInput" id="UserName" placeholder="이메일"
                                   value={userEmail}
                                   onChange={(e) => setUserEmail(e.target.value)}/>
                            <div className="InputEndIcon">

                            </div>
                        </div>

                        <div className="SignInputBox">
                            <div className="InputBoxIcon">

                                <MdOutlinePassword size={20} />

                            </div>
                            <input type={"password"} className="SignInput" id="UserName" placeholder="비밀번호"
                                   value={password}
                                   onChange={(e) => setPassword(e.target.value)}/>
                            <div className="InputEndIcon">

                            </div>
                        </div>

                        <button type={"submit"} name="SignIn">로그인</button>

                    </form>

                    <div className="SignEtcLink">계정이 없으신가요? 회원가입</div>

                </div>

            </div>

        </div>
    );
}

export default LoginPage;
