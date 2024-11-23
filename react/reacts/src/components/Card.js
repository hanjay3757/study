const Card = ({ 
  job = '',
  grade = '',
  isFlipped = false,
  imageUrl,
  onFlip,
  draggable,
  onDragStart,
  onDragOver,
  onDrop,
  index,
  isActive,
  isAttacker,
  isTarget 
}) => {
  const cardClasses = [
    'card',
    job,
    grade,
    isFlipped ? 'flipped' : '',
    isActive ? 'active' : '',
    isAttacker ? 'attacker' : '',
    isTarget ? 'target' : ''
  ].filter(Boolean).join(' ');

  return (
    <div 
      className={cardClasses}
      draggable={draggable}
      onDragStart={onDragStart}
      onDragOver={onDragOver}
      onDrop={onDrop}
      onClick={onFlip}
      style={{
        transform: `perspective(1000px) rotateX(0deg) rotateY(${isFlipped ? '180deg' : '0deg'})`,
        transition: 'transform 0.6s'
      }}
    >
      <div className="card-front">
        <div className="card-content">
          <div className="card-title">{job}</div>
          <div className="card-grade">{grade}</div>
          {imageUrl && <img src={imageUrl} alt={job} className="card-image" />}
        </div>
      </div>
      <div className="card-back">
        <div className="card-back-design"></div>
      </div>
    </div>
  );
};

export default Card; 