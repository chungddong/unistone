import {useEffect, useState} from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import axios from "axios";
import './App.css';

import LoginPage from './Page/LoginPage';
import MainPage from './Page/MainPage';
import SignUpPage from "./Page/SignUpPage";
import Layout from "./Page/Layout";

function App() {


    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/SignUp" element={<SignUpPage />} />
                <Route path="/main" element={<MainPage />} />
                <Route path="/layout" element={<Layout />} />
            </Routes>
        </Router>
    );
}


export default App;
