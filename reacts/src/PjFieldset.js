import React from 'react';
import Card from './Card.js';
import Stars from './Stars.js';
import CardArea from './CardArea.js';

function PjFieldset({ pj, pjListItem, clearPj, pjId }) {
  return (
    <fieldset>
      <legend>
        {pjListItem && (
          <>
            {pjListItem.no} {pjListItem.name} <Stars amount={pjListItem.level} />{' '}
            {pjListItem.gold}💰 {pjListItem.content}
          </>
        )}
      </legend>
      <button onClick={clearPj}>참여인원 비우기</button>
      <CardArea pjId={pjId}>
        {pj.map((character, index) => (
          <Card key={index} job={character.job} grade={character.grade} />
        ))}
      </CardArea>
    </fieldset>
  );
}

export default PjFieldset;
