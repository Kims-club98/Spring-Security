import React from "react";

const JoinPage = () => {
  return (
  <>
    <div className="row my-5 justify-content-center">
      <div className="col-8 col-md-6 col-lg-4">
        <h3 className="text-center mb-5">회원가입</h3>
        <form className="frm">
          <div className="input-group my-2">
            <div className="input-group-text">이 름</div>
            <input
              className="form-control"
              name="name"
              type="text"
              placeholder="이름을 입력해주세요"
              value={formData.name}
              onChange={handleChange}
            />
          </div>
          <div className="input-group my-2">
            <div className="input-group-text">이 메 일</div>
            <input
              className="form-control"
              name="email"
              value={formData.email}
              onChange={handleChange}
            />
          </div>
          <div className="input-group my-2">
            <div className="input-group-text">비밀번호</div>
            <input
              className="form-control"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
            />
          </div>
          <div className="input-group my-2">
            <div className="input-group-text">권 한</div>
            <select
              className="form-select"
              name="role"
              value={formData.role}
              onChange={handleChange}
            >
              <option value="ROLE_USER">ROLE_USER</option>
              <option value="ROLE_MANAGER">ROLE_MANAGER</option>
              <option value="ROLE_ADMIN">ROLE_ADMIN</option>
            </select>
          </div>
          <div className="my-3">
            <button
              className="btn btn-success w-100"
              type="button"
              onClick={handleSignup}
            >
              회원가입
            </button>
          </div>
          <div className="text-end mt-3">
            <a href="/login">로그인</a>
          </div>
        </form>
      </div>
    </div>
  </>
  );
};

export default JoinPage;
