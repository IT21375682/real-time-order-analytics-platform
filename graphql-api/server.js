const { ApolloServer, gql } = require('apollo-server');
const { Pool } = require('pg');

// PostgreSQL setup
const pool = new Pool({
  host: 'localhost',
  port: 5432,
  user: 'analytics',
  password: 'analytics',
  database: 'analytics_db',
});

// Define GraphQL schema
const typeDefs = gql`
  type Order {
    orderId: String!
    userId: String!
    productId: String!
    quantity: Int!
    price: Float!
    createdAt: String!
  }

  type Query {
    orders: [Order]
    totalRevenueToday: Float!
    ordersPerMinute: Int!
  }
`;

// Define resolvers (logic behind the GraphQL queries)
const resolvers = {
  Query: {
    orders: async () => {
      const res = await pool.query('SELECT * FROM order_entity');
      return res.rows;
    },
    totalRevenueToday: async () => {
      const result = await pool.query(
        `SELECT COALESCE(SUM(price * quantity), 0) AS revenue
         FROM order_entity
         WHERE created_at::date = CURRENT_DATE`
      );
      return parseFloat(result.rows[0].revenue);
    },
    ordersPerMinute: async () => {
      const result = await pool.query(
        `SELECT COUNT(*) AS count
         FROM order_entity
         WHERE created_at > NOW() - INTERVAL '1 minute'`
      );
      return parseInt(result.rows[0].count, 10);
    },
  },
};

// Initialize and run Apollo Server
const server = new ApolloServer({ typeDefs, resolvers });

server.listen(4000).then(({ url }) => {
  console.log(`Server ready at ${url}`);
  console.log("output",resolvers)
});
