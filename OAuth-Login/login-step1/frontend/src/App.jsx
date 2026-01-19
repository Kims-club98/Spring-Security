import "bootstrap/dist/css/bootstrap.min.css"
import { Route, Routes, useNavigate } from "react-router-dom"
import BoardPage from "./components/pages/BoardPage"
import HomePage from "./components/pages/HomePage"
import IsTokenExpriation from "./components/auth/IsTokenExpiration"
import { useEffect, useState } from "react"


function App() {
  const navigate = useNavigate()
  // token 상태를 관리하기 위해서 useState로 변경하기
  const [token, setToken] = useState(() => {
    const token = window.localStorage.getItem('accessToken') // 최상위존재 window는 생략가능
    return token
  })
  // TODO - Token의 유효시간을 Check -> 파기 혹은 유지???
  const isTokenExpire = IsTokenExpriation(token)
  console.log(isTokenExpire) // true면 만료, false면 유효
  useEffect(()=>{
    if(isTokenExpire){
      window.localStorage.clear()
      navigate('/',{replace:true})
    }
  },[token, isTokenExpire, navigate])
  return (
    <>
      <Routes>
        <Route path="/home" element={<HomePage />} />
        <Route path="/board" element={<BoardPage />} />
      </Routes>
    </>
  )
}
export default App
