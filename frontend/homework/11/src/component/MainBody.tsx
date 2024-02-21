import { useEffect, useState } from 'react'
import { Api } from '../types/quotes.types';
import { Filter } from './Filter';
import { NewQuote } from './NewQuote';
import { Quote } from './Quote';
import './MainBody.scss'

export function MainBody() {
    const [allQuotes, setAllQuotes] = useState<Api[]>([]);
    const [quotes, setQuotes] = useState<Api[]>([]);
    const [select, setTags] = useState<string[]>([]);
  
    const all = {
      all: allQuotes,
      allState: setAllQuotes
    }
  
    const filt = {
      filt: select,
      filtState: setTags
    }
  
    useEffect(() => {
      setQuotes(
        allQuotes.filter((quote) => select.length !== 0 ? quote.tags.filter(item => select.includes(item)).length > 0 : allQuotes)
      )
    }, [select, allQuotes]);
  
    const fetchData = () => {
  
      fetch("https://api.quotable.io/quotes/random?limit=3").
        then((response) => response.json()).
        then((data: Api[]) => {
          setAllQuotes(data);
        })
    };
    useEffect(() => {
      fetchData()
    }, []);
    return (
      <div className='main'>
        <NewQuote head={all} />
        <Filter head={filt} />
        {
          quotes.map((quote) => {
            const get = {
              head: filt,
              foot: quote
            }
            return (
              <Quote key={quote._id} quote={get} />
            )
          })
        }
      </div>
    );
}