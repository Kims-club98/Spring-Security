import "bootstrap/dist/css/bootstrap.min.css"
import { Route, Routes } from "react-router-dom"
import HomePage from "./components/pages/HomePage"
import BoardPage from "./pages/BoardPage"

function App() {
// TODO - Token의 유효시간을 Check -> 파기 혹은 유지???
const token = localStorage.getItem('token') // 최상위존재 window는 생략가능
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
