import React from "react";
import "./App.css";
import {
  Container,
  Grid,
  Menu,
  Dropdown,
  Button,
  Form,
  FormField,
  Table,
  Header,
  Label,
  List,
  Segment,
  Divider,
} from "semantic-ui-react";
import { withRouter } from "react-router-dom";
import { toast } from "react-toastify";

class UserSearchBook extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      books: [],
      title: "",
    };
  }
  exit = () => {
    fetch("http://localhost:8081/logout", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
    })
      .then((r) => {
        if (r.ok) {
          return r;
        }
        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("Something went wrong :("));
        }
      })
      .then((response) => {
        toast.success("Good Bye User!");
        setTimeout(() => {
          this.props.history.push("/");
        }, 1000);
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };
  handleSubmit = (e) => {
    e.preventDefault();
    this.searchUsers();
  };
  handleChange = (e) => {
    const { currentTarget } = e;
    const { value, name } = currentTarget;

    this.setState({ [name]: value });
  };
  searchUsers = () => {
    fetch(
      `http://localhost:8081/api/users/search_book?title=${this.state.title}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
      }
    )
      .then((r) => {
        if (r.ok) {
          return r;
        }
        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("Bir hata oluştu"));
        }
      })
      .then((r) => r.json())
      .then((data) => {
        if (data.active === false || data.length === 0) {
          return Promise.reject(new Error("Book not found"));
        }

        toast.success("Search process successful");
        this.setState({ books: data });
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };
  render = () => {
    const { books } = this.state;
    return (
      <div className="App">
        <Container>
          <Grid>
            <Grid.Row columns="equal" centered>
              <Grid.Column width={16}>
                <Menu size="large">
                  <Dropdown item text="Book">
                    <Dropdown.Menu>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/dashboard");
                          }}
                        >
                          List of the Books
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/usersearchbook");
                          }}
                        >
                          Search the Books
                        </Button>
                      </Dropdown.Item>
                    </Dropdown.Menu>
                  </Dropdown>
                  <Menu.Menu position="right">
                    <Menu.Item id="page">
                      <Button
                        type="button"
                        onClick={() => {
                          this.props.history.push("/userpage");
                        }}
                      >
                        Home
                      </Button>
                    </Menu.Item>
                  </Menu.Menu>
                  <Menu.Menu position="right">
                    <Menu.Item id="menu">
                      <button
                        color="red"
                        id="exit"
                        onClick={() => {
                          this.exit();
                        }}
                      >
                        Sign Out
                      </button>
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
                  onSubmit={this.handleSubmit}
                  onReset={(e) => {
                    e.preventDefault();
                    this.setState({
                      title: "",
                    });
                  }}
                >
                  <FormField>
                    <Header id="head" size="huge">
                      Search Books
                    </Header>
                    <br></br>
                    <label id="txt">Title</label>
                    <Form.Input
                      type="text"
                      name="title"
                      required
                      value={this.state.title}
                      onChange={this.handleChange}
                    />
                  </FormField>
                  <br></br>
                  <Button.Group fluid>
                    <Button color="teal" type="reset">
                      Reset
                    </Button>
                    <Button type="submit">Search</Button>
                  </Button.Group>
                </Form>
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </Container>
        <Container>
          <Grid>
            <Grid.Row columns="equal" centered>
              <Grid.Column width={16}>
                <Header id="head" size="huge">
                  Books
                </Header>
                <Table celled>
                  <Table.Header>
                    <Table.Row>
                      <Table.HeaderCell colSpan="2" textAlign="right">
                        Title
                      </Table.HeaderCell>
                      <Table.HeaderCell>Author</Table.HeaderCell>
                      <Table.HeaderCell>Publisher</Table.HeaderCell>
                      <Table.HeaderCell>Publication Year</Table.HeaderCell>
                    </Table.Row>
                  </Table.Header>

                  <Table.Body>
                    {books &&
                      books.map((value, index) => (
                        <Table.Row>
                          <Table.Cell>
                            <Label ribbon>{index + 1}</Label>
                          </Table.Cell>
                          <Table.Cell>{value.title}</Table.Cell>
                          <Table.Cell>{value.author}</Table.Cell>
                          <Table.Cell>{value.publisher}</Table.Cell>
                          <Table.Cell>{value.publication_year}</Table.Cell>
                        </Table.Row>
                      ))}
                  </Table.Body>

                  <Table.Footer>
                    <Table.Row>
                      <Table.HeaderCell colSpan="6"></Table.HeaderCell>
                    </Table.Row>
                  </Table.Footer>
                </Table>
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
}

export default withRouter(UserSearchBook);
