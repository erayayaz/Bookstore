import React from "react";
import "./App.css";
import {
  Container,
  Grid,
  Form,
  FormField,
  Button,
  Dropdown,
  Menu,
  Header,
  List,
  Segment,
  Divider,
} from "semantic-ui-react";
import { withRouter } from "react-router-dom";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";

class PublisherUpdate extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      publisherId: 0,
      address: "",
      addressError: null,
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
        toast.success("Good Bye Admin!");
        setTimeout(() => {
          this.props.history.push("/");
        }, 1000);
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };
  handleChange = (e) => {
    const { currentTarget } = e;
    const { value, name } = currentTarget;

    this.setState({ [name]: value });
  };

  handleSubmit = (e) => {
    e.preventDefault();

    const { address } = this.state;

    if (address.length < 3 || address.length > 50) {
      this.setState({
        addressError: "Invalid address",
      });
      return;
    }

    fetch(
      `http://localhost:8081/api/admins/update_publisher/${this.state.publisherId}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ address }),
        credentials: "include",
      }
    )
      .then((r) => {
        if (r.ok) {
          return r;
        }
        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("Something went wrong"));
        }
      })
      .then((r) => r.json())
      .then((response) => {
        toast.success("Address update succesfull");
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };

  render = () => {
    const { addressError } = this.state;
    return (
      <div className="App">
        <Container>
          <Grid>
            <Grid.Row columns="equal" centered>
              <Grid.Column width={16}>
                <Menu size="large">
                  <Dropdown item text="User">
                    <Dropdown.Menu>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/register");
                          }}
                        >
                          Add
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/userdelete");
                          }}
                        >
                          Delete
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/userupdate");
                          }}
                        >
                          Update
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/usersearch");
                          }}
                        >
                          Search
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/userlist");
                          }}
                        >
                          List
                        </Button>
                      </Dropdown.Item>
                    </Dropdown.Menu>
                  </Dropdown>
                  <Dropdown item text="Book">
                    <Dropdown.Menu>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/addbook");
                          }}
                        >
                          Add
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/bookdelete");
                          }}
                        >
                          Delete
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/bookupdate");
                          }}
                        >
                          Update
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/booksearch");
                          }}
                        >
                          Search
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/booklist");
                          }}
                        >
                          List
                        </Button>
                      </Dropdown.Item>
                    </Dropdown.Menu>
                  </Dropdown>
                  <Dropdown item text="Author">
                    <Dropdown.Menu>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/authoradd");
                          }}
                        >
                          Add
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/authordelete");
                          }}
                        >
                          Delete
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/authorupdate");
                          }}
                        >
                          Update
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/authorsearch");
                          }}
                        >
                          Search
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/authorlist");
                          }}
                        >
                          List
                        </Button>
                      </Dropdown.Item>
                    </Dropdown.Menu>
                  </Dropdown>
                  <Dropdown item text="Publisher">
                    <Dropdown.Menu>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/publisheradd");
                          }}
                        >
                          Add
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/publisherdelete");
                          }}
                        >
                          Delete
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/publisherupdate");
                          }}
                        >
                          Update
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/publishersearch");
                          }}
                        >
                          Search
                        </Button>
                      </Dropdown.Item>
                      <Dropdown.Item>
                        <Button
                          type="button"
                          onClick={() => {
                            this.props.history.push("/publisherlist");
                          }}
                        >
                          List
                        </Button>
                      </Dropdown.Item>
                    </Dropdown.Menu>
                  </Dropdown>
                  <Menu.Menu position="right">
                    <Menu.Item id="page">
                      <Button
                        type="button"
                        onClick={() => {
                          this.props.history.push("/adminpage");
                        }}
                      >
                        Home
                      </Button>
                    </Menu.Item>
                  </Menu.Menu>
                  <Menu.Menu position="right">
                    <Menu.Item id="menu">
                      <Button
                        type="button"
                        color="primary"
                        onClick={() => {
                          this.props.history.push("/adminregister");
                        }}
                      >
                        Admin Sign Up
                      </Button>
                    </Menu.Item>
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
                      publisherId: 0,
                      address: "",
                    });
                  }}
                >
                  <FormField>
                    <Header id="head" size="huge">
                      Update Publisher
                    </Header>
                    <br></br>
                    <label id="txt">Publisher Id </label>
                    <Form.Input
                      type="number"
                      name="publisherId"
                      required
                      value={this.state.publisherId}
                      onChange={this.handleChange}
                    />
                  </FormField>
                  <FormField>
                    <label id="txt">Address</label>
                    <Form.Input
                      type="text"
                      name="address"
                      required
                      error={addressError}
                      value={this.state.address}
                      onChange={this.handleChange}
                    />
                  </FormField>
                  <br></br>
                  <Button.Group fluid>
                    <Button color="teal" type="reset">
                      Reset
                    </Button>
                    <Button type="submit">Update</Button>
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
}
export default withRouter(PublisherUpdate);
