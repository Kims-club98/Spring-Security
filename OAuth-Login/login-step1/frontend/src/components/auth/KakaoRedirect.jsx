// Kakao 정보와 Backend와 연결되있는 부분

import axios from 'axios'
import React, { useEffect } from 'react'

const KakaoRedirect = () => {
  const code = new URL(window.location.href).searchParams.get('code')
  console.log(code)
  useEffect(()=>{
    const kakaoLogin = async() => {
      const response = await axios.post(`${import.meta.env.VITE_SPRING_IP}member/kakao/doLogin`,{code: code}) // /member/kakao ... (//로 출력되므로 member앞 / X)(VITE_SPRING_IP뒤에 / 있음)
      console.log(response)
    }
    kakaoLogin()
  },[])
  return (
    <>
      loading ...
    </>
  )
}

export default KakaoRedirect
