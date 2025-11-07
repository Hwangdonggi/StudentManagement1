// 백엔드 주소 (필요하면 포트 변경)
export const API_BASE = 'http://localhost:8080/api';


export async function postJSON(path, data) {
const res = await fetch(`${API_BASE}${path}`, {
method: 'POST',
headers: { 'Content-Type': 'application/json' },
credentials: 'include', // 세션 쿠키 포함
body: JSON.stringify(data)
});
return res.json();
}


export async function getJSON(path) {
const res = await fetch(`${API_BASE}${path}`, {
credentials: 'include'
});
return res.json();
}