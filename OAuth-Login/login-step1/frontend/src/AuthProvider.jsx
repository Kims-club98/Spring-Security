import React, { Children } from 'react'
import { Navigate } from 'react-router-dom';

const AuthProvider = ({children}) => {
  console.log(children) // children은 함수값(컴포넌트 == 합수값)
  const token = window.localStorage.getItem('token'); // 로컬의 token값 가져오기

  // ▶ token이 없을 때 이곳으로...
  if(!token){
    return <Navigate to='/' />
  }
  return children
}// end of AuthProvider

export default AuthProvider

