import axios from "axios"

export const RefreshTokenDB = async () => {
  const refreshToken = window.localStorage.getItem("refreshToken")
  if(!refreshToken){
    throw new Error('No refresh token')
  }
  const url = `${import.meta.env.VITE_SPRING_IP}auth/refresh`
  try {
    const res = await axios.post(
      url,
      {refreshToken: refreshToken},
      {headers: {'Content-Type': 'application/json'}}
    )
    console.log(res)
    return res
  } catch (error) {
    console.error("refresh token 요청 실패!!!", error)
    throw error 
  }
} // end fo RefreshTokenDB(수명이 짧은 AccessToken을 갱신목적, Refresh Token을 서버로 보내, 새로운 토큰으로 바꿔오는 작업[== 로그인 연장 처리 로직])