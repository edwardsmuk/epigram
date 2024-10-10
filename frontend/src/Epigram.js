import React, {  useState, useEffect, Component } from 'react';
import {Container,Alert, Card, CardBody, CardTitle, CardSubtitle, CardText,CardHeader,CardFooter, Button, Row, Col, Modal,ModalHeader, ModalHead, ModalBody,ModalFooter, FormGroup, FormLabel,FormControl, Form, Label,Input} from 'reactstrap';

function EpigramComponent() {
  const [epigram, setEpigram] = useState({ content: '', author: '' }); // Current epigram object
  const [error, setError] = useState(null); // Error state
  const [isEnabled, setIsEnabled] = useState(true); // Toggle for auto-fetching of Epigram
  const [newEpigram, setNewEpigram] = useState({ Content: '', author: '' }); // New epigram form
  const [loading, setLoading] = useState(false); // Loading state for triggering manual fetch
  const [show, setShow] = useState(false);

  const [visible, setVisible] = useState(false); //Alert
  const onDismiss = () => setVisible(false); //Alert

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);



  // Function to fetch a random epigram from the API
  const fetchEpigram = async () => {
    setLoading(true);
    try {
      const response = await fetch('/api/v1/epigrams/random/1'); // Random Epigram API endpoint
      if (!response.ok) {
        throw new Error('Network response was not Ok');
      }
      const data = await response.json();
      setEpigram(data); // Lets update the fetched epigram
      setError(null); // Lets reset the error upon successful fetch
    } catch (error) {
      setError(error.message); // Lets set the error if fetching fails
    } finally {
      setLoading(false);
    }
  };

  // Fetch  random epigram upon component mount and when enabled
  useEffect(() => {
    let intervalId;

    // Fetch a random epigram immediately
    fetchEpigram();

    // Set interval if automatic fetching is enabled
    if (isEnabled) {
      intervalId = setInterval(fetchEpigram, 10000); // Let's fetch every 10 seconds
    }

    // Let's cleanup the interval on component unmount or when disabled
    return () => clearInterval(intervalId);
  }, [isEnabled]);

  // Toggling automatic fetching
  const toggleFetching = () => {
    setIsEnabled(!isEnabled);
  };

  // Handle input change for the form to save a new epigram
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewEpigram({ ...newEpigram, [name]: value });
  };

  // Save the new epigram to the API
  const saveNewEpigram = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('/api/v1/epigrams', {  // API endpoint to create a new Epigram
        method: 'POST',
        headers: {
          'Content-Type': 'application/hal+json',
        },
        body: JSON.stringify(newEpigram),
      });

      if (!response.ok) {
        setShow(false);
        throw new Error('Unable to save the epigram');
      }
      else{
        setShow(false);
      }

      const savedEpigram = await response.json();
      setEpigram(savedEpigram); // Update UI with saved epigram
      setNewEpigram({ content: '', author: '' }); // Reset form after saving
      setError(null); // Reset error on successful save
    } catch (error) {
      setError(error.message); // Set error if saving fails
    }
  };

  return (
  <div className="row">
    <div className="col-md-12">
    <Container className="fluid">
    <div className="d-flex justify-content-center" style={{ height: '100vh' }}>
    <Alert color="danger" isOpen={visible} toggle={onDismiss}>Unable to save the epigram!</Alert>
            <Card
              className="my-2"
              style={{
                width: '60rem',
                height: '24rem',
                borderColor: '#fff'
              }}
            >
              <CardHeader
                style={{
                borderColor: '#fff',
                backgroundColor: '#fff'
                 }}
              >

              </CardHeader>
              <CardBody>

                {loading ? (
                      <p>Loading...</p>
                    ) : error ? (
                      <p style={{ color: 'red' }}>{error}</p>
                    ) : (
                      <div>
                        <CardText><p>"{epigram.content}"</p></CardText>
                        <CardTitle style={{ color: "#131F48", fontStyle: 'italic' }} tag="h5"><p>- {epigram.author}</p></CardTitle>
                      </div>
                )}
              </CardBody>
              <CardFooter
              style={{
                borderColor: '#fff',
                backgroundColor: '#fff'
               }}
              >
                <Row>
                    {/* Toggle button for enabling/disabling automatic loading */}
                    <Col><Button style={{ backgroundColor: "#131F48", borderColor: "#131F48" }} onClick={toggleFetching}  >{isEnabled ? 'Disable Automatic Loading' : 'Enable Automatic Loading'}</Button> </Col>
                     {/* Button for manually loading a new epigram */}
                    <Col><Button style={{ backgroundColor: "#131F48", borderColor: "#131F48" }} onClick={fetchEpigram} disabled={loading} >Load Another Epigram</Button> </Col>
                    <Col>
                    <Button style={{ backgroundColor: "#131F48", borderColor: "#131F48" }} onClick={handleShow}>Add Your Own Epigram</Button>
                    <Modal isOpen={show} onHide={handleClose}>
                            <ModalHeader closeButton>
                              Add a New Epigram
                            </ModalHeader>
                            <ModalBody>
                              <Form onSubmit={saveNewEpigram}>
                                <FormGroup className="mb-3" >
                                  <Label>Content</Label>
                                  <Input
                                    type="textarea"
                                    name="content"
                                    placeholder="Content"
                                    value={newEpigram.content}
                                    onChange={handleInputChange}
                                    autoFocus
                                    required
                                  />
                                </FormGroup>
                                <FormGroup
                                  className="mb-3"
                                >
                                  <Label>Author</Label>
                                  <Input
                                    type="text"
                                    name="author"
                                    placeholder="author"
                                    value={newEpigram.author}onChange={handleInputChange}
                                    required
                                  />
                                </FormGroup>
                              </Form>
                            </ModalBody>
                            <ModalFooter>
                              <Button style={{ backgroundColor: "#131F48", borderColor: "#131F48" }} variant="secondary" onClick={handleClose}>
                                Cancel
                              </Button>
                              <Button style={{ backgroundColor: "#131F48", borderColor: "#131F48" }} type="submit" variant="primary" onClick={saveNewEpigram}>
                                Save
                              </Button>
                            </ModalFooter>
                          </Modal>
                    </Col>
                </Row>
              </CardFooter>
            </Card>
          </div>
        </Container>
    </div>
  </div>

  );
}

export default EpigramComponent;
