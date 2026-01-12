import React from 'react'

const GoogleRedirect = () => {
  // Google에서 보내주는 인가코드 받기
  const code = new URL(window.Location.href).searchParams.get('code')
  console.log("코드:"+code)
  return (
    <div>
      Loading 중입니다......
    </div>
  )
}

export default GoogleRedirect
