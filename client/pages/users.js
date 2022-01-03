import React,{useEffect,useState} from 'react'
import axios from 'axios'

const users=()=> {
const [posts, setPosts]=useState([])
const getPosts = async () => {
  try {
const userPosts = await axios.get("https://jsonplaceholder.typicode.com/posts")
    
    setPosts(userPosts.data);  // set State
  
  } catch (err) {
    console.error(err.message);
  }
};
  
  useEffect(()=>{
    
    getPosts()
},[])  // includes empty dependency array
return (
    <div>
     <h1>useEffect</h1>
     <ul>
       {posts.map(post=>(
         <li key={post.id}>{post.title}</li>
       ))}
     </ul>
    </div>
  );
}
export default users;