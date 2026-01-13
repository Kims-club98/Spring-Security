import axios from 'axios'
import React, { useEffect } from 'react'

const GoogleRedirect = () => {
  // 구글에서 보내주는 인가코드 받기
  const code = new URL(window.location.href).searchParams.get('code')
  console.log(code)
  useEffect(()=>{
    const googleLogin = async() => {
      const response = await axios.post("http://localhost:8000/memeber/google/doLogin",{code:code})
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