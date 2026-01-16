import "bootstrap/dist/css/bootstrap.min.css"
import { Route, Routes } from "react-router-dom"
import BoardPage from "./components/pages/BoardPage"
import HomePage from "./components/pages/HomePage"
import IsTokenExpriation from "./components/auth/IsTokenExpriation"


function App() {

  const token = localStorage.getItem('token') // 최상위존재 window는 생략가능
  // TODO - Token의 유효시간을 Check -> 파기 혹은 유지???
  const IsTokenExpriation = IsTokenExpriation(token)
  console.log(IsTokenExpriation) // true면 만료, false면 유효
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
