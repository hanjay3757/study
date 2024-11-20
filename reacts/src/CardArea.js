import './App.css';

function CardArea({ children, pjId }) {
  function pjClick(pjId){
    if(pjId > 0){
      alert(pjId);
    }
  }

  return (
    <div id='card_area' onClick={()=>pjClick(pjId)}>
      {children}
    </div>
  );
}

export default CardArea;