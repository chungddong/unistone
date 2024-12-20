import {useEffect, useState} from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import axios from "axios";
import './App.css';

import LoginPage from './Page/LoginPage';
import MainPage from './Page/MainPage';
import SignUpPage from "./Page/SignUpPage";

function App() {


    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/SignUp" element={<SignUpPage />} />
                <Route path="/main" element={<MainPage />} />
            </Routes>
        </Router>
    );
}


export default App;
