import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Register from "./Register";
import Login from "./Login";
import Dashboard from "./Dashboard";
import Main from "./Main";
import AdminPage from "./AdminPage";
import AdminRegister from "./AdminRegister";
import UserDelete from "./UserDelete";
import UserUpdate from "./UserUpdate";
import UserSearch from "./UserSearch";
import AddBook from "./AddBook";
import BookDelete from "./BookDelete";
import BookUpdate from "./BookUpdate";
import BookSearch from "./BookSearch";
import AuthorAdd from "./AuthorAdd";
import AuthorDelete from "./AuthorDelete";
import AuthorUpdate from "./AuthorUpdate";
import UserPage from "./UserPage";
import UserSearchBook from "./UserSearchBook";
import AuthorSearch from "./AuthorSearch";
import PublisherAdd from "./PublisherAdd";
import PublisherDelete from "./PublisherDelete";
import PublisherUpdate from "./PublisherUpdate";
import PublisherSearch from "./PublisherSearch";
import UserList from "./UserList";
import BookList from "./BookList";
import AuthorList from "./AuthorList";
import PublisherList from "./PublisherList";

export default function App() {
  return (
    <Router>
      <div>
        <Switch>
          <Route exact path="/">
            <Main />
          </Route>

          <Route path="/register">
            <Register />
          </Route>
          <Route path="/login">
            <Login showRegisterLink />
          </Route>
          <Route path="/dashboard">
            <Dashboard />
          </Route>
          <Route path="/adminpage">
            <AdminPage />
          </Route>
          <Route path="/adminregister">
            <AdminRegister />
          </Route>
          <Route path="/userdelete">
            <UserDelete />
          </Route>
          <Route path="/userupdate">
            <UserUpdate />
          </Route>
          <Route path="/usersearch">
            <UserSearch />
          </Route>
          <Route path="/addbook">
            <AddBook />
          </Route>
          <Route path="/bookdelete">
            <BookDelete />
          </Route>
          <Route path="/bookupdate">
            <BookUpdate />
          </Route>
          <Route path="/booksearch">
            <BookSearch />
          </Route>
          <Route path="/authoradd">
            <AuthorAdd />
          </Route>
          <Route path="/authordelete">
            <AuthorDelete />
          </Route>
          <Route path="/authorupdate">
            <AuthorUpdate />
          </Route>
          <Route path="/userpage">
            <UserPage />
          </Route>
          <Route path="/usersearchbook">
            <UserSearchBook />
          </Route>
          <Route path="/authorsearch">
            <AuthorSearch />
          </Route>
          <Route path="/publisheradd">
            <PublisherAdd />
          </Route>
          <Route path="/publisherdelete">
            <PublisherDelete />
          </Route>
          <Route path="/publisherupdate">
            <PublisherUpdate />
          </Route>
          <Route path="/publishersearch">
            <PublisherSearch />
          </Route>
          <Route path="/userlist">
            <UserList />
          </Route>
          <Route path="/booklist">
            <BookList />
          </Route>
          <Route path="/authorlist">
            <AuthorList />
          </Route>
          <Route path="/publisherlist">
            <PublisherList />
          </Route>
          <Route path="*">
            <strong>404 | Something went wrong.</strong>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
