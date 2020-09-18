import React from "react";
import {
  Button,
  Menu,
  Container,
  Grid,
  Header,
  Divider,
  Segment,
  List,
} from "semantic-ui-react";
import { useHistory } from "react-router-dom";

const Main = ({ showRegisterLink }) => {
  const history = useHistory();
  return (
    <div className="Menu">
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
      <Container className="Header" text>
        <Header id="content" size="huge">
          Welcome to the Bookstore!
        </Header>
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

export default Main;
