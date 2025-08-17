require 'json'

cardinal_directions = %w[north south west east]
speed_levels = %w[slow medium fast]

cardinal_directions.each do |direction|
  speed_levels.each do |speed|
    block_name = "#{speed}_conveyor_#{direction}"
    model_path = "scalarutils:block/#{block_name}"
    json_content = {
      variants: {
        "" => {
          model: model_path
        }
      }
    }
    File.write("#{block_name}.json", JSON.pretty_generate(json_content))
  end
end