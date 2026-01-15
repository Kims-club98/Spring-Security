import { useEffect, useState } from "react"
import { Button, Container, Nav, Navbar } from "react-bootstrap"
import { Link } from "react-router-dom"

const Header = () => {
  //로그인 상태 관리 - false면 로그아웃 버튼이 보이지 않음
  const[email, setEmail] = useState('')
  const [name, setName] = useState('')
  // 리엑트 - 동기화 - 새로 그려진다 - 언제??
  const [isLoggedIn, setIsLoggedIn] = useState(false)
  useEffect(() => {
    const token = window.localStorage.getItem('token')
    const email = window.localStorage.getItem('email')
    const name = window.localStorage.getItem('name')
    if(token){
      setIsLoggedIn(true)
      setEmail(email)
      setName(name)
    }
  })
  const onLogout = () => {
    setIsLoggedIn(false)
    window.localStorage.clear()
  }
  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary">
        <Container>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Link to="/" className="nav-link">Home</Link>
              <Link to="/login" className="nav-link">로그인</Link>
              <Link to="/board" className="nav-link">게시판</Link>
            </Nav>
          {isLoggedIn &&
            <>   
              <Link to='/' className="nav-link">{email}</Link>
              <Button className="btn btn-danger" onClick={onLogout}>로그아웃</Button>
            </>
          }
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  )
}

export default Header