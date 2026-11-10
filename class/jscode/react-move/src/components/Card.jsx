export default function Card({ title, children }) {
  return (
    <div className="card">
      {title && <h2>{title}</h2>}
      <div className="card-body">
        {children}
      </div>
    </div>
  );
}