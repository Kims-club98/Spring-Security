import axios from 'axios'
import React, { useEffect } from 'react'
import {useNavigate} from 'react-router-dom' 

const GoogleRedirect = () => {
  const navigate = useNavigate()
  // 구글에서 보내주는 인가코드 받기
  // Google에서 5173번 서버로 응답 보낼 때 ?code=12345678
  const code = new URL(window.location.href).searchParams.get('code')
  console.log(code)
  useEffect(()=>{
    const googleLogin = async() => {

      const response = await axios.post(`${import.meta.env.VITE_SPRING_IP}member/google/doLogin`,{code:code}) // 정적 함수 {}로 작성하여 유동적으로 변화 가능하도록 조작한다(만일 변경이 힐요한 사항 발생 시 수정이 쉬워짐)
      const token = response.data.token
      const email = response.data.email
      const name = response.data.name
      console.log("Access token : "+token)
      window.localStorage.setItem("token",token)
      window.localStorage.setItem("email",email) // google Profile 정보 나온 이메일
      window.localStorage.setItem("name",name) // google Profile 정보로 나온 이름
      // Token이 생성되는 경우 Home 화면으로 이동함...
      if(token){
        navigate("/home")
      }else{
        alert("Access Token이 없습니다.")
      };
    };
    googleLogin();
  },[])
  return (
    <>
      loading ...
    </>
  )
}

export default GoogleRedirect