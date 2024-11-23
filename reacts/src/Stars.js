import './App.css';

function Stars({amount}) {
  let stars = '';
  for (let i = 0; i < amount; i++) {
    stars += '⭐';
  }  

  return (
    <>
      {stars}
    </>
  );
}

export default Stars;