import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './hotel-my-suffix.reducer';
import { IHotelMySuffix } from 'app/shared/model/hotel-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IHotelMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IHotelMySuffixState = IPaginationBaseState;

export class HotelMySuffix extends React.Component<IHotelMySuffixProps, IHotelMySuffixState> {
  state: IHotelMySuffixState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        activePage: 0,
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { hotelList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="hotel-my-suffix-heading">
          <Translate contentKey="vtgApp.hotel.home.title">Hotels</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vtgApp.hotel.home.createLabel">Create new Hotel</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('name')}>
                  <Translate contentKey="vtgApp.hotel.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('starRank')}>
                  <Translate contentKey="vtgApp.hotel.starRank">Star Rank</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('slogan')}>
                  <Translate contentKey="vtgApp.hotel.slogan">Slogan</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('address')}>
                  <Translate contentKey="vtgApp.hotel.address">Address</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('priceFrom')}>
                  <Translate contentKey="vtgApp.hotel.priceFrom">Price From</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('priceTo')}>
                  <Translate contentKey="vtgApp.hotel.priceTo">Price To</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('viewCount')}>
                  <Translate contentKey="vtgApp.hotel.viewCount">View Count</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('convenient')}>
                  <Translate contentKey="vtgApp.hotel.convenient">Convenient</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('introduction')}>
                  <Translate contentKey="vtgApp.hotel.introduction">Introduction</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('confirmBookingType')}>
                  <Translate contentKey="vtgApp.hotel.confirmBookingType">Confirm Booking Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ratingId')}>
                  <Translate contentKey="vtgApp.hotel.ratingId">Rating Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('latitude')}>
                  <Translate contentKey="vtgApp.hotel.latitude">Latitude</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('longitude')}>
                  <Translate contentKey="vtgApp.hotel.longitude">Longitude</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('provinceId')}>
                  <Translate contentKey="vtgApp.hotel.provinceId">Province Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hotelType')}>
                  <Translate contentKey="vtgApp.hotel.hotelType">Hotel Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('filePath1')}>
                  <Translate contentKey="vtgApp.hotel.filePath1">File Path 1</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('filePath2')}>
                  <Translate contentKey="vtgApp.hotel.filePath2">File Path 2</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('filePath3')}>
                  <Translate contentKey="vtgApp.hotel.filePath3">File Path 3</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('filePath4')}>
                  <Translate contentKey="vtgApp.hotel.filePath4">File Path 4</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('filePath5')}>
                  <Translate contentKey="vtgApp.hotel.filePath5">File Path 5</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('placeId')}>
                  <Translate contentKey="vtgApp.hotel.placeId">Place Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hotelList.map((hotel, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${hotel.id}`} color="link" size="sm">
                      {hotel.id}
                    </Button>
                  </td>
                  <td>{hotel.name}</td>
                  <td>{hotel.starRank}</td>
                  <td>{hotel.slogan}</td>
                  <td>{hotel.address}</td>
                  <td>{hotel.priceFrom}</td>
                  <td>{hotel.priceTo}</td>
                  <td>{hotel.viewCount}</td>
                  <td>{hotel.convenient}</td>
                  <td>{hotel.introduction}</td>
                  <td>{hotel.confirmBookingType}</td>
                  <td>{hotel.ratingId}</td>
                  <td>{hotel.latitude}</td>
                  <td>{hotel.longitude}</td>
                  <td>{hotel.provinceId}</td>
                  <td>
                    <Translate contentKey={`vtgApp.HotelType.${hotel.hotelType}`} />
                  </td>
                  <td>{hotel.filePath1}</td>
                  <td>{hotel.filePath2}</td>
                  <td>{hotel.filePath3}</td>
                  <td>{hotel.filePath4}</td>
                  <td>{hotel.filePath5}</td>
                  <td>{hotel.placeId}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${hotel.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hotel.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hotel.id}/delete`} color="danger" size="sm">
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
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ hotel }: IRootState) => ({
  hotelList: hotel.entities,
  totalItems: hotel.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HotelMySuffix);
