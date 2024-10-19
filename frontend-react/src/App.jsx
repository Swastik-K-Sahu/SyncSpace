import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import { over } from 'stompjs';
import './App.css'

let stompClient = null;

function App() {
  const [documentContent, setDocumentContent] = useState('');
  const [connected, setConnected] = useState(false);

  const connectWebSocket = () => {
    const socket = new SockJS('http://localhost:8080/ws');
    stompClient = over(socket);
    stompClient.connect({}, () => {
      setConnected(true);
      stompClient.subscribe('/topic/updates', (message) => {
        onMessageReceived(JSON.parse(message.body));
      });
    });
  };

  const onMessageReceived = (message) => {
    setDocumentContent(message.content);
  };

  const sendMessage = () => {
    if (stompClient) {
      stompClient.send('/app/edit', {}, JSON.stringify({ id: 'doc1', content: documentContent }));
    }
  };

  useEffect(() => {
    connectWebSocket();
  }, []);

  return (
    <div className="min-h-screen bg-gradient-to-r from-blue-400 to-green-400 p-8 flex justify-center items-center">
    <div className="bg-white shadow-lg p-6 rounded-lg">
    <h1 className="text-4xl font-bold mb-6 text-gray-800">Collaborate in Real-Time</h1>
    <textarea
      className="w-full h-80 p-4 text-xl border-2 border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
      value={documentContent}
      onChange={(e) => setDocumentContent(e.target.value)}
    />
    <button
      onClick={sendMessage}
      className="mt-4 px-6 py-3 bg-blue-600 text-white font-bold rounded-lg shadow-lg hover:bg-blue-700"
    >
      Update Document
    </button>
    </div>
  </div>
  );
}

export default App
