import axios from 'axios'
import React, { useEffect } from 'react'

const GoogleRedirect = () => {
  // 구글에서 보내주는 인가코드 받기
  // Google에서 5173번 서버로 응답 보낼 때 ?code=12345678
  const code = new URL(window.location.href).searchParams.get('code')
  console.log(code)
  useEffect(()=>{
    const googleLogin = async() => {

      const response = await axios.post(`${import.meta.env.VITE_SPRING_IP}/memeber/google/doLogin`,{code:code}) // 정적 함수 {}로 작성하여 유동적으로 변화 가능하도록 조작한다(만일 변경이 힐요한 사항 발생 시 수정이 쉬워짐)
      console.log(response)
    }
    googleLogin();
  },[])
  return (
    <>
      loading ...
    </>
  )
}

export default GoogleRedirect