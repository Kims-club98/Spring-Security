import React from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import AuthProvider from './AuthProvider.jsx'
import JoinPage from './components/pages/JoinPage.jsx'
import GoogleRedirect from './components/auth/GoogleRedirect.jsx'
import KakaoRedirect from './components/auth/KakaoRedirect.jsx'
import LoginView from './components/auth/LoginView.jsx'


createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginView/>} />
        <Route path="/JoinForm" element={<JoinPage />} />
        <Route path="/oauth/google/redirect" element={<GoogleRedirect />} />
        <Route path="/oauth/kakao/redirect" element={<KakaoRedirect />} />
        {/* ▼ 로그인 인증이 필요한 곳으로 필요한 컴포넌트를 보호한다. */}
        <Route path='/*' element={
          <AuthProvider>
            <App />
          </AuthProvider>
        } />
      </Routes>     
    </BrowserRouter>
  </React.StrictMode>
)
