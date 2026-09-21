import React from "react";
import { createRoot } from "react-dom/client";

function App() {
  return (
    <main>
      <h1>42 Starter</h1>
      <p>React frontend is ready.</p>
    </main>
  );
}

createRoot(document.getElementById("root")).render(<App />);
