import React, { useState } from "react";
import {
  Container,
  Grid,
  Form,
  Button,
  Menu,
  Header,
  List,
  Segment,
  Divider,
} from "semantic-ui-react";
import { useHistory } from "react-router-dom";
import "./App.css";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";

const Login = ({ showRegisterLink }) => {
  const history = useHistory();
  const [usernamePassword, setUsernamePassword] = useState({
    username: "",
    password: "",
  });

  const [usernamePasswordError, setUsernamePasswordError] = useState({
    username: null,
    password: null,
  });
  const handleChange = (e) => {
    const { currentTarget } = e;
    const { value, name } = currentTarget;

    setUsernamePassword({ ...usernamePassword, [name]: value });
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    const { username, password } = usernamePassword;

    if (username.length < 5 || username.length > 255) {
      setUsernamePasswordError({
        ...usernamePasswordError,
        username: "Lutfen kullanıcı adini kontrol ediniz",
      });
      return;
    } else {
      setUsernamePasswordError({
        username: null,
      });
    }

    if (password.length < 3 || password.length > 255) {
      setUsernamePasswordError({
        ...usernamePasswordError,
        password: "Lutfen şifrenizi kontrol ediniz",
      });
      return;
    }
    const formData = new URLSearchParams();
    formData.append("username", username);
    formData.append("password", password);
    fetch("http://localhost:8081/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: formData,
      credentials: "include",
    })
      .then((r) => {
        if (r.ok) {
          return r;
        }
        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("Bir hata oluştu"));
        }
      })
      .then((r) => r.json())
      .then((response) => {
        if (response.role.authority === "ROLE_ADMIN") {
          toast.success(
            "Admin login succesfull.. Welcome " + response.username + " !"
          );
          setTimeout(() => {
            history.push("/adminpage");
          }, 1000);
        } else if (response.role.authority === "ROLE_USER") {
          toast.success(
            "User login succesfull.. Welcome " + response.username + " !"
          );
          setTimeout(() => {
            history.push("/userpage");
          }, 1000);
        }
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };

  return (
    <div className="App">
      <Container>
        <Grid>
          <Grid.Row columns="equal" centered>
            <Grid.Column width={16}>
              <Menu size="large">
                <Menu.Item>
                  <Button
                    type="button"
                    onClick={() => {
                      history.push("/");
                    }}
                  >
                    Home
                  </Button>
                </Menu.Item>

                <Menu.Menu position="right">
                  <Menu.Item id="menu">
                    <Button
                      type="button"
                      color="primary"
                      onClick={() => {
                        history.push("/login");
                      }}
                    >
                      Sign In
                    </Button>
                  </Menu.Item>
                </Menu.Menu>
              </Menu>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Container>
      <Container>
        <Grid>
          <Grid.Row columns="equal" centered>
            <Grid.Column width={8}>
              <Form
                onSubmit={handleSubmit}
                onReset={(e) => {
                  e.preventDefault();
                  setUsernamePassword({ username: "", password: "" });
                }}
              >
                <Form.Field>
                  <Header id="head" size="huge">
                    Login
                  </Header>

                  <br></br>

                  <label id="txt">Username</label>
                  <Form.Input
                    type="email"
                    name="username"
                    error={usernamePasswordError.username}
                    required
                    value={usernamePassword.username}
                    onChange={handleChange}
                  />
                </Form.Field>

                <Form.Field>
                  <label id="txt">Password</label>
                  <Form.Input
                    type="password"
                    name="password"
                    error={usernamePasswordError.password}
                    required
                    value={usernamePassword.password}
                    onChange={handleChange}
                  />
                </Form.Field>
                <br></br>
                <Button.Group fluid>
                  <Button color="teal" type="reset">
                    Reset
                  </Button>
                  <Button type="submit">Login</Button>
                </Button.Group>
              </Form>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Container>
      <Segment
        inverted
        vertical
        style={{ margin: "30em 0em 0em", padding: "3em 0em" }}
      >
        <Container textAlign="center">
          <Grid divided inverted stackable>
            <Grid.Column width={3}>
              <Header inverted as="h4" content="About" />
              <List link inverted>
                <List.Item as="a">Services</List.Item>
                <List.Item as="a">Portfolio</List.Item>
                <List.Item as="a">Pricing</List.Item>
                <List.Item as="a">Customers</List.Item>
                <List.Item as="a">Careers</List.Item>
              </List>
            </Grid.Column>
            <Grid.Column width={3}>
              <Header inverted as="h4" content="Resources" />
              <List link inverted>
                <List.Item as="a">Docs</List.Item>
                <List.Item as="a">Blogs</List.Item>
                <List.Item as="a">eBooks</List.Item>
                <List.Item as="a">Webinars</List.Item>
              </List>
            </Grid.Column>
            <Grid.Column width={3}>
              <Header inverted as="h4" content="Team" />
              <List link inverted>
                <List.Item as="a">Help</List.Item>
                <List.Item as="a">Sales</List.Item>
                <List.Item as="a">Advertise</List.Item>
              </List>
            </Grid.Column>
            <Grid.Column width={7}>
              <Header inverted as="h4" content="Contact" />
              <List.Item text-align="center">
                <i class="map marker alternate icon"></i>
                İstanbul,Kadıköy
              </List.Item>

              <List.Item text-align="center">
                <i class="call icon"></i>
                (0534) 694 04 78
              </List.Item>

              <List.Item text-align="center">
                <i class="mail icon"></i>
                eray19ayaz@gmail.com
              </List.Item>
            </Grid.Column>
          </Grid>
          <Divider inverted section />
          <List horizontal inverted divided link size="small">
            <List.Item as="a" href="#">
              Site Map
            </List.Item>
            <List.Item as="a" href="#">
              Terms and Conditions
            </List.Item>
            <List.Item as="a" href="#">
              Privacy Policy
            </List.Item>
          </List>
          <div class="ui center aligned container">
            <h4 as="a">&copy; Copyright 2020</h4>
            <a href="https://www.facebook.com/">
              <i class="facebook square icon big"></i>
            </a>
            <a href="https://twitter.com/">
              <i class="twitter square icon big"></i>
            </a>
            <a href="https://www.linkedin.com/in/erayayaz/">
              <i class="linkedin square icon big"></i>
            </a>
            <a href="https://github.com/erayayaz">
              <i class="github square icon big"></i>
            </a>
          </div>
        </Container>
      </Segment>
    </div>
  );
};

export default Login;
