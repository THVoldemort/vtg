import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './hotel-room-my-suffix.reducer';
import { IHotelRoomMySuffix } from 'app/shared/model/hotel-room-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHotelRoomMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class HotelRoomMySuffix extends React.Component<IHotelRoomMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { hotelRoomList, match } = this.props;
    return (
      <div>
        <h2 id="hotel-room-my-suffix-heading">
          <Translate contentKey="vtgApp.hotelRoom.home.title">Hotel Rooms</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vtgApp.hotelRoom.home.createLabel">Create new Hotel Room</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.introduction">Introduction</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.numOfAdult">Num Of Adult</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.numOfChild">Num Of Child</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.priceEst">Price Est</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.priceAct">Price Act</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.quantity">Quantity</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.filePath1">File Path 1</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.filePath2">File Path 2</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.filePath3">File Path 3</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.filePath4">File Path 4</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.hotelRoom.filePath5">File Path 5</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hotelRoomList.map((hotelRoom, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${hotelRoom.id}`} color="link" size="sm">
                      {hotelRoom.id}
                    </Button>
                  </td>
                  <td>{hotelRoom.type}</td>
                  <td>{hotelRoom.introduction}</td>
                  <td>{hotelRoom.numOfAdult}</td>
                  <td>{hotelRoom.numOfChild}</td>
                  <td>{hotelRoom.priceEst}</td>
                  <td>{hotelRoom.priceAct}</td>
                  <td>{hotelRoom.quantity}</td>
                  <td>{hotelRoom.filePath1}</td>
                  <td>{hotelRoom.filePath2}</td>
                  <td>{hotelRoom.filePath3}</td>
                  <td>{hotelRoom.filePath4}</td>
                  <td>{hotelRoom.filePath5}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${hotelRoom.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hotelRoom.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hotelRoom.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ hotelRoom }: IRootState) => ({
  hotelRoomList: hotelRoom.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HotelRoomMySuffix);
